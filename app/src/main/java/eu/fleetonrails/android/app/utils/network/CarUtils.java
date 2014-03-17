package eu.fleetonrails.android.app.utils.network;

import android.content.ContextWrapper;

import org.androidannotations.annotations.Background;

import eu.fleetonrails.android.app.models.car.CarList;
import eu.fleetonrails.android.app.services.network.BaseService;
import eu.fleetonrails.android.app.services.network.CarService;
import retrofit.RestAdapter;

/**
 * Created by alan
 * on 15/03/2014.
 */
public class CarUtils {
    @Background
    public static CarList index(ContextWrapper contextWrapper) {
        RestAdapter restAdapter;
        restAdapter = new RestAdapter.Builder()
                .setServer(BaseService.serverPath)
                .setRequestInterceptor(new HttpInterceptor(contextWrapper))
                .build();

        CarService carService = restAdapter.create(CarService.class);

        return carService.indexSync();
    }
}
