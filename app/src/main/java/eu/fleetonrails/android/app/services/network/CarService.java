package eu.fleetonrails.android.app.services.network;

import eu.fleetonrails.android.app.models.car.CarList;
import eu.fleetonrails.android.app.models.car.CarObject;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by alan
 * on 15/03/2014.
 */
public interface CarService {

    @GET("/v1/cars")
    void indexASync(Callback<CarList> callback);

    @GET("/v1/cars")
    CarList indexSync();

    @GET("/v1/cars/{id}")
    void showASync(@Path("id") int id, Callback<CarObject> callback);

    @GET("v1/cars/{id}")
    CarObject showSync(@Path("id") int id);
}
