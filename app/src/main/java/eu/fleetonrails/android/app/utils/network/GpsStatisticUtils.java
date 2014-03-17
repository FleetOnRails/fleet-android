package eu.fleetonrails.android.app.utils.network;

import android.content.ContextWrapper;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;

import org.androidannotations.annotations.Background;

import eu.fleetonrails.android.app.models.gps_statistics.GpsStatisticAttributes;
import eu.fleetonrails.android.app.models.gps_statistics.GpsStatisticObject;
import eu.fleetonrails.android.app.models.gps_statistics.LocationAttributes;
import eu.fleetonrails.android.app.services.network.BaseService;
import eu.fleetonrails.android.app.services.network.GpsStatisticService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

/**
 * Created by alan
 * on 17/03/2014.
 */
public class GpsStatisticUtils {

    @Background
    public static void create(double latitude, double longitude, double speed, int id, ContextWrapper contextWrapper) {
        LocationAttributes locationAttributes = new LocationAttributes();
        locationAttributes.setLatitude(latitude);
        locationAttributes.setLongitude(longitude);

        GpsStatisticAttributes gpsStatisticAttributes = new GpsStatisticAttributes();
        gpsStatisticAttributes.setKmh(speed);
        gpsStatisticAttributes.setLocation_attributes(locationAttributes);

        GpsStatisticObject gpsStatisticObject = new GpsStatisticObject();
        gpsStatisticObject.setGps_statistic(gpsStatisticAttributes);

        OkHttpClient client = new OkHttpClient();

        RestAdapter restAdapter;
        restAdapter = new RestAdapter.Builder()
                .setServer(BaseService.serverPath)
                .setRequestInterceptor(new HttpInterceptor(contextWrapper))
                .setClient(new OkClient(client))
                .build();

        GpsStatisticService gpsStatisticService = restAdapter.create(GpsStatisticService.class);
        gpsStatisticService.create(id, gpsStatisticObject, new Callback<GpsStatisticObject>() {
            @Override
            public void success(GpsStatisticObject gpsStatisticObject, Response response) {
                Log.d("Success", Integer.toString(response.getStatus()));
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Error", error.toString());
            }
        });
    }
}
