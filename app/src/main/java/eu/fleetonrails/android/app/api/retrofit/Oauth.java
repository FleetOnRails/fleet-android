package eu.fleetonrails.android.app.api.retrofit;

import eu.fleetonrails.android.app.model.network.oauth.OauthCallback;
import eu.fleetonrails.android.app.model.network.oauth.OauthCredential;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by alan on 07/03/2014.
 */
public interface Oauth {
    static String serverPath = "http://fleet-api.raven.com";

    @POST("/oauth/token")
    public void getBearerFromCredentials(
            @Body OauthCredential oauthCredential,
            Callback<OauthCallback> callback);
}
