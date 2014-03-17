package eu.fleetonrails.android.app.models.gps_statistics;

/**
 * Created by alan
 * on 17/03/2014.
 */
public class LocationAttributes {
    public double latitude, longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
