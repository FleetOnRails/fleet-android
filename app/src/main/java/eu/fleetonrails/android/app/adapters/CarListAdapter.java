package eu.fleetonrails.android.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import eu.fleetonrails.android.app.R;
import eu.fleetonrails.android.app.models.car.CarObject;

/**
 * Created by alan
 * on 17/03/2014.
 */
public class CarListAdapter extends ArrayAdapter<CarObject> {
    private ArrayList<CarObject> carObjects;

    public CarListAdapter(Context context, int textViewResourceId, ArrayList<CarObject> objects) {
        super(context, textViewResourceId, objects);
        this.carObjects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_list_car, null);
        }

        CarObject carObject = carObjects.get(position);

        if (carObject != null) {
            TextView make = (TextView) view.findViewById(R.id.make);
            TextView model = (TextView) view.findViewById(R.id.model);

            if (make != null) {
                make.setText(carObject.getCar().getMake() + " ");
            }

            if (model != null) {
                model.setText(carObject.getCar().getModel());
            }
        }

        return view;
    }
}
