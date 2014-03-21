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
            TextView tt = (TextView) view.findViewById(R.id.toptext);
            TextView ttd = (TextView) view.findViewById(R.id.toptextdata);
            TextView mt = (TextView) view.findViewById(R.id.middletext);
            TextView mtd = (TextView) view.findViewById(R.id.middletextdata);
            TextView bt = (TextView) view.findViewById(R.id.bottomtext);
            TextView btd = (TextView) view.findViewById(R.id.desctext);

            // check to see if each individual textview is null.
            // if not, assign some text!
            if (tt != null){
                tt.setText("Reg: ");
            }
            if (ttd != null){
                ttd.setText(carObject.car.registration);
            }
            if (mt != null){
                mt.setText("Make: ");
            }
            if (mtd != null){
                mtd.setText(carObject.car.make);
            }
            if (bt != null){
                bt.setText("Model: ");
            }
            if (btd != null){
                btd.setText(carObject.car.model);
            }
        }

        return view;
    }
}
