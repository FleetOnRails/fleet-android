package eu.fleetonrails.android.app.services.network;

import eu.fleetonrails.android.app.models.Oauth;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by alan Kehoe
 * on 08/03/2014.
 */
public interface OauthService {

    @FormUrlEncoded
    @POST("/oauth/token")
    void getBearerFromCredentials(
            @Field("grant_type") String grant_type,
            @Field("client_id") String client_id,
            @Field("client_secret") String client_secret,
            @Field("username") String username,
            @Field("password") String password,
            Callback<Oauth> callback);
}
