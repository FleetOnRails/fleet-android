package eu.fleetonrails.android.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;

import eu.fleetonrails.android.app.models.gps_statistics.GpsStatisticAttributes;
import eu.fleetonrails.android.app.models.gps_statistics.GpsStatisticObject;
import eu.fleetonrails.android.app.models.gps_statistics.LocationAttributes;
import eu.fleetonrails.android.app.services.network.BaseService;
import eu.fleetonrails.android.app.services.network.GpsStatisticService;
import eu.fleetonrails.android.app.utils.network.HttpInterceptor;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;


public class TrackingActivity extends ActionBarActivity {
    private String make, model, registration;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Allow synchronous network calls
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);

        Intent intent = getIntent();

        make = intent.getExtras().getString("Make");
        model = intent.getExtras().getString("Model");
        registration = intent.getExtras().getString("Registration");
        id = intent.getExtras().getInt("ID");

        updateView();
        sendStat();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tracking, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void updateView() {

        TextView tt = (TextView) findViewById(R.id.toptext);
        TextView ttd = (TextView) findViewById(R.id.toptextdata);
        TextView mt = (TextView) findViewById(R.id.middletext);
        TextView mtd = (TextView) findViewById(R.id.middletextdata);
        TextView bt = (TextView) findViewById(R.id.bottomtext);
        TextView btd = (TextView) findViewById(R.id.desctext);

        if (tt != null) {
            tt.setText("Reg: ");
        }
        if (ttd != null) {
            ttd.setText(registration);
        }
        if (mt != null) {
            mt.setText("Make: ");
        }
        if (mtd != null) {
            mtd.setText(make);
        }
        if (bt != null) {
            bt.setText("Model: ");
        }
        if (btd != null) {
            btd.setText(model);
        }
    }

    public void sendStat() {
        LocationAttributes locationAttributes = new LocationAttributes();
        locationAttributes.setLatitude(52.676181);
        locationAttributes.setLongitude(-6.289091);

        GpsStatisticAttributes gpsStatisticAttributes = new GpsStatisticAttributes();
        gpsStatisticAttributes.setKmh(65.4);
        gpsStatisticAttributes.setLocation_attributes(locationAttributes);

        GpsStatisticObject gpsStatisticObject = new GpsStatisticObject();
        gpsStatisticObject.setGps_statistic(gpsStatisticAttributes);

        OkHttpClient client = new OkHttpClient();

        RestAdapter restAdapter;
        restAdapter = new RestAdapter.Builder()
                .setServer(BaseService.serverPath)
                .setRequestInterceptor(new HttpInterceptor(TrackingActivity.this))
                .setClient(new OkClient(client))
                .build();

        GpsStatisticService gpsStatisticService = restAdapter.create(GpsStatisticService.class);
        gpsStatisticService.create(id, gpsStatisticObject, new Callback<GpsStatisticObject>() {
            @Override
            public void success(GpsStatisticObject gpsStatisticObject, Response response) {
                Log.d("gps", "hello");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Error", error.toString());
            }
        });
    }
}
