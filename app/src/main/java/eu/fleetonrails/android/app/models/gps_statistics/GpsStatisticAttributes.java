package eu.fleetonrails.android.app.models.gps_statistics;

/**
 * Created by alan
 * on 17/03/2014.
 */
public class GpsStatisticAttributes {
    public double kmh;
    public LocationAttributes location_attributes;

    public double getKmh() {
        return kmh;
    }

    public void setKmh(double kmh) {
        this.kmh = kmh;
    }

    public LocationAttributes getLocation_attributes() {
        return location_attributes;
    }

    public void setLocation_attributes(LocationAttributes location_attributes) {
        this.location_attributes = location_attributes;
    }
}
