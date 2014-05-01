package eu.fleetonrails.android.app.utils.network;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;

import retrofit.RequestInterceptor;

/**
 * Created by alan Kehoe
 * on 09/03/2014.
 */
public class HttpInterceptor implements RequestInterceptor {
    private String bearer;

    public HttpInterceptor(ContextWrapper contextWrapper) {
        SharedPreferences fleetPreferences = contextWrapper.getSharedPreferences("FleetPreferences", Context.MODE_PRIVATE);
        bearer = fleetPreferences.getString("AccessToken", "");
    }

    @Override
    public void intercept(RequestFacade request) {
        request.addHeader("Authorization", "Bearer " + bearer);
        request.addHeader("Accept", "application/json");
        request.addHeader("Content-Type", "application/json");
    }
}
