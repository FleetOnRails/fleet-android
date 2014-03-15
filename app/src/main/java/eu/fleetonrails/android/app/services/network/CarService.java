package eu.fleetonrails.android.app.services.network;

import eu.fleetonrails.android.app.models.car.CarList;
import eu.fleetonrails.android.app.models.car.CarObject;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by alan
 * on 15/03/2014.
 */
public interface CarService {

    @GET("/v1/cars")
    void index(Callback<CarList> callback);

    @GET("/v1/cars/1")
    void show(Callback<CarObject> callback);
}
