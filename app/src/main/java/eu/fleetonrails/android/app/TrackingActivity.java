package eu.fleetonrails.android.app;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import eu.fleetonrails.android.app.utils.network.GpsStatisticUtils;


public class TrackingActivity extends ActionBarActivity implements LocationListener {
    private String make, model, registration;
    private int id;

    private LocationManager locationManager;

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

        /********** get Gps location service LocationManager object ***********/
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 10, this);
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

    @Override
    public void onLocationChanged(Location location) {
        TextView latitudeView = (TextView) findViewById(R.id.latitudeView);
        TextView longitudeView = (TextView) findViewById(R.id.longitudeView);
        TextView speedView = (TextView) findViewById(R.id.speedView);

        latitudeView.setText("Lat: " + location.getLatitude());
        longitudeView.setText("Lon: " + location.getLongitude());
        speedView.setText("Speed: " + location.getSpeed() * 3.6 + "km/h");

        GpsStatisticUtils.create(
                location.getLatitude(),
                location.getLongitude(),
                location.getSpeed() * 3.6,
                id,
                TrackingActivity.this);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        /******** Called when User on Gps  *********/
        Toast.makeText(getBaseContext(), "Gps turned on ", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        /******** Called when User off Gps *********/
        Toast.makeText(getBaseContext(), "Gps turned off ", Toast.LENGTH_LONG).show();
    }
}
