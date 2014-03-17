package eu.fleetonrails.android.app.services.network;

import eu.fleetonrails.android.app.models.gps_statistics.GpsStatisticObject;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by alan
 * on 17/03/2014.
 */
public interface GpsStatisticService {

    @POST("/v1/cars/{id}/gps_statistics")
    void create(
            @Path("id") int id,
            @Body GpsStatisticObject gpsStatisticObject,
            Callback<GpsStatisticObject> cb);
}
