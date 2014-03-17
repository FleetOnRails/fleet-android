package eu.fleetonrails.android.app;

import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import eu.fleetonrails.android.app.utils.network.GpsStatisticUtils;


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

        getLocation();
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

    public void getLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, false);
        final Location location = locationManager.getLastKnownLocation(bestProvider);

        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    double latitude = 0.0;
                    double longitude = 0.0;
                    double speed;
                    if (location.hasSpeed()) {
                        speed = location.getSpeed();
                    } else {
                        speed = 0.0;
                    }
                    try {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }

                    GpsStatisticUtils.create(latitude, longitude, speed, id, TrackingActivity.this);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
