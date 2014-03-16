package eu.fleetonrails.android.app.models.car;

/**
 * Created by alan
 * on 15/03/2014.
 */
public class CarAttributes {
    public int id;
    public int owner_id;
    public String make;
    public String model;
    public String registration;
    public String owner_type;

    public int getId() {
        return id;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getRegistration() {
        return registration;
    }

    public String getOwner_type() {
        return owner_type;
    }
}
