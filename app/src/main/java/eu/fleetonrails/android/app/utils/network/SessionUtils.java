package eu.fleetonrails.android.app.utils.network;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import org.androidannotations.annotations.Background;

import eu.fleetonrails.android.app.LoginActivity;
import eu.fleetonrails.android.app.MainActivity;
import eu.fleetonrails.android.app.models.Oauth;
import eu.fleetonrails.android.app.models.me.MeObject;
import eu.fleetonrails.android.app.services.network.BaseService;
import eu.fleetonrails.android.app.services.network.MeService;
import eu.fleetonrails.android.app.services.network.OauthService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by alan Kehoe
 * on 09/03/2014.
 */
public class SessionUtils {

    @Background
    public static void userLogin(final ContextWrapper contextWrapper, String username, String password) {
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
                        Intent intent = new Intent(contextWrapper, MainActivity.class);

                        SharedPreferences fleetPreferences = contextWrapper.getSharedPreferences("FleetPreferences", Context.MODE_PRIVATE);
                        SharedPreferences.Editor prefEditor = fleetPreferences.edit();
                        prefEditor.putString("AccessToken", oauth.access_token);
                        prefEditor.putString("Refresh Token", oauth.refresh_token);
                        prefEditor.commit();
                        Toast.makeText(contextWrapper, "Logged in!", Toast.LENGTH_LONG).show();

                        contextWrapper.startActivity(intent);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        SharedPreferences fleetPreferences = contextWrapper.getSharedPreferences("FleetPreferences", Context.MODE_PRIVATE);
                        SharedPreferences.Editor prefEditor = fleetPreferences.edit();
                        prefEditor.putString("AccessToken", null);
                        prefEditor.putString("Refresh Token", null);
                        prefEditor.commit();
                        Toast.makeText(contextWrapper, "Login Failed!", Toast.LENGTH_LONG).show();
                        Log.d("error", error.toString());
                    }
                }
        );
    }

    @Background
    public static void isLoggedIn(final ContextWrapper contextWrapper) {
        RestAdapter restAdapter;
        restAdapter = new RestAdapter.Builder()
                .setServer(BaseService.serverPath)
                .setRequestInterceptor(new HttpInterceptor(contextWrapper))
                .build();

        MeService meService = restAdapter.create(MeService.class);
        meService.ping(new Callback<MeObject>() {
            @Override
            public void success(MeObject meObject, Response response) {
                Toast.makeText(contextWrapper, "Already Logged In!", Toast.LENGTH_LONG).show();
//                Log.d("status", "Already logged in");
            }

            @Override
            public void failure(RetrofitError error) {
                Intent intent = new Intent(contextWrapper, LoginActivity.class);
                contextWrapper.startActivity(intent);
            }
        });
    }

    @Background
    public static void logout(final ContextWrapper contextWrapper) {
        Intent intent = new Intent(contextWrapper, LoginActivity.class);

        SharedPreferences fleetPreferences = contextWrapper.getSharedPreferences("FleetPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = fleetPreferences.edit();
        prefEditor.putString("AccessToken", null);
        prefEditor.putString("Refresh Token", null);
        prefEditor.commit();

        Toast.makeText(contextWrapper, "Logged Out!", Toast.LENGTH_LONG).show();
        contextWrapper.startActivity(intent);
    }
}
