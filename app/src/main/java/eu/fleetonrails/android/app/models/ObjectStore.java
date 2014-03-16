package eu.fleetonrails.android.app.models;

import java.util.ArrayList;

import eu.fleetonrails.android.app.models.car.CarObject;

/**
 * Created by alan
 * on 16/03/2014.
 */
public class ObjectStore {
    private static ArrayList<CarObject> carObjects = new ArrayList<CarObject>();

    public static void addCarObject(CarObject carObject) {
        carObjects.add(carObject);
    }

    public static ArrayList<CarObject> getCarObjects() {
        return carObjects;
    }
}
