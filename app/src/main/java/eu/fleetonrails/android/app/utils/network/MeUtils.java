package eu.fleetonrails.android.app.utils.network;

import android.content.ContextWrapper;
import android.util.Log;

import org.androidannotations.annotations.Background;

import eu.fleetonrails.android.app.models.me.MeObject;
import eu.fleetonrails.android.app.services.network.BaseService;
import eu.fleetonrails.android.app.services.network.MeService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by alan
 * on 09/03/2014.
 */
public class MeUtils {

    @Background
    public static void index(ContextWrapper contextWrapper) {
        RestAdapter restAdapter;
        restAdapter = new RestAdapter.Builder()
                .setServer(BaseService.serverPath)
                .setRequestInterceptor(new HttpInterceptor(contextWrapper))
                .build();

        MeService meService = restAdapter.create(MeService.class);
        meService.index(new Callback<MeObject>() {
            @Override
            public void success(MeObject meObject, Response response) {
                Log.d("user", meObject.me.first_name);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Error", error.toString());
            }
        });
    }
}
