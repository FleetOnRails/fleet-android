package eu.fleetonrails.android.app.utils.network;

import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import org.androidannotations.annotations.Background;

import eu.fleetonrails.android.app.models.me.MeAttributes;
import eu.fleetonrails.android.app.models.me.MeObject;
import eu.fleetonrails.android.app.services.network.MeService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by alan on 09/03/2014.
 */
public class MeUtil {

    @Background
    public static void index(ContextWrapper contextWrapper) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(contextWrapper.getBaseContext());

        RestAdapter restAdapter;
        restAdapter = new RestAdapter.Builder()
                .setServer(preferences.getString("base_api_url", ""))
                .setRequestInterceptor(new HttpInterceptor(contextWrapper))
                .build();

        MeService service = restAdapter.create(MeService.class);
        service.getData(new Callback<MeObject>() {
            @Override
            public void success(MeObject meObject, Response response) {
                MeAttributes me = meObject.me;

                Log.d("me pojo", me.id + ", " + me.first_name);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Error", error.toString());
            }
        });
    }
}
