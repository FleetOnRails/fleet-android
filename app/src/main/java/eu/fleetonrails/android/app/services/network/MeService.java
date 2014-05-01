package eu.fleetonrails.android.app.services.network;

import eu.fleetonrails.android.app.models.me.MeObject;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by alan Kehoe
 * on 08/03/2014.
 */
public interface MeService {

    @GET("/v1/me")
    void index(Callback<MeObject> callback);

//    TODO - Remove redundant service
    @GET("/v1/me")
    void ping(Callback<MeObject> callback);
}
