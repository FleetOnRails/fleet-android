package eu.fleetonrails.android.app.utils.network;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import org.androidannotations.annotations.Background;

import eu.fleetonrails.android.app.models.Oauth;
import eu.fleetonrails.android.app.services.network.BaseService;
import eu.fleetonrails.android.app.services.network.OauthService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by alan Kehoe
 * on 09/03/2014.
 */
public class LoginUtil {

    @Background
    public static void login(final ContextWrapper contextWrapper, String username, String password) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(contextWrapper.getBaseContext());

        RestAdapter restAdapter;
        restAdapter = new RestAdapter.Builder()
                .setServer(BaseService.serverPath)
                .build();

        OauthService oauthService = restAdapter.create(OauthService.class);

        oauthService.getBearerFromCredentials("password",
                sharedPreferences.getString("oauth_client_id", ""),
                sharedPreferences.getString("oauth_client_secret", ""),
                username, password, new Callback<Oauth>() {
                    @Override
                    public void success(Oauth oauth, Response response) {
                        SharedPreferences fleetPreferences = contextWrapper.getSharedPreferences("FleetPreferences", Context.MODE_PRIVATE);
                        SharedPreferences.Editor prefEditor = fleetPreferences.edit();
                        prefEditor.putString("AccessToken", oauth.access_token);
                        prefEditor.commit();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("error", error.toString());
                    }
                }
        );

        SharedPreferences fleetPreferences = contextWrapper.getSharedPreferences("FleetPreferences", Context.MODE_PRIVATE);
        Log.d("Bearer ", fleetPreferences.getString("AccessToken", ""));
    }
}
