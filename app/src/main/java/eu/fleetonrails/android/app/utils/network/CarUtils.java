package eu.fleetonrails.android.app.utils.network;

import android.content.ContextWrapper;
import android.util.Log;

import org.androidannotations.annotations.Background;

import eu.fleetonrails.android.app.models.car.CarList;
import eu.fleetonrails.android.app.models.car.CarObject;
import eu.fleetonrails.android.app.services.network.BaseService;
import eu.fleetonrails.android.app.services.network.CarService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by alan
 * on 15/03/2014.
 */
public class CarUtils {
    @Background
    public static void index(ContextWrapper contextWrapper) {
        RestAdapter restAdapter;
        restAdapter = new RestAdapter.Builder()
                .setServer(BaseService.serverPath)
                .setRequestInterceptor(new HttpInterceptor(contextWrapper))
                .build();

        CarService carService = restAdapter.create(CarService.class);
        carService.index(new Callback<CarList>() {

            @Override
            public void success(CarList carList, Response response) {
                for (CarObject carObject : carList.cars) {
                    Log.d("car", carObject.car.make);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Error", error.toString());
            }
        });
    }
}
