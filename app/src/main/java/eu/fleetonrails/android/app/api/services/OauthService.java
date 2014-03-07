package eu.fleetonrails.android.app.api.services;

import android.util.Log;

import org.androidannotations.annotations.Background;

import eu.fleetonrails.android.app.api.retrofit.Oauth;
import eu.fleetonrails.android.app.model.network.oauth.OauthCallback;
import eu.fleetonrails.android.app.model.network.oauth.OauthCredential;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by alan on 07/03/2014.
 */
public class OauthService {
    private static Oauth oauth;
    private static OauthCallback callback;

    @Background
    public static OauthCallback getOauthFromClientCredentials(OauthCredential oauthCredential) {
        RestAdapter restAdapter;
        restAdapter = new RestAdapter.Builder()
                .setServer(Oauth.serverPath)
                .build();

        oauth = restAdapter.create(Oauth.class);

        oauth.getBearerFromCredentials(oauthCredential, new Callback<OauthCallback>() {
            @Override
            public void success(OauthCallback oauthCallback, Response response) {
                Log.d("oauth", oauthCallback.getAccess_token());
                callback = oauthCallback;
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("error", error.toString());
            }
        });

        return callback;
    }
}
