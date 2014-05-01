package eu.fleetonrails.android.app;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import eu.fleetonrails.android.app.adapters.CarListAdapter;
import eu.fleetonrails.android.app.models.car.CarObject;
import eu.fleetonrails.android.app.utils.network.CarUtils;
import eu.fleetonrails.android.app.utils.network.SessionUtils;


public class ListCarActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Allow synchronous network calls
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        super.onCreate(savedInstanceState);

        // Check if logged in
        SessionUtils.isLoggedIn(ListCarActivity.this);

        // Get all cars
        ArrayList<CarObject> carObjects = CarUtils.index(this).getCars();

        setListAdapter(new CarListAdapter(this, R.layout.activity_list_car, carObjects));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        CarObject selectedValue = (CarObject) getListAdapter().getItem(position);

        Intent intent = new Intent(this, TrackingActivity.class);
        intent.putExtra("Make", selectedValue.car.make);
        intent.putExtra("Model", selectedValue.car.model);
        intent.putExtra("Registration", selectedValue.car.registration);
        intent.putExtra("ID", selectedValue.car.id);

        startActivity(intent);
    }
}

