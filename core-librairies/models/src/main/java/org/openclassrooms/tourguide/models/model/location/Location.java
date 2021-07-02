package org.openclassrooms.tourguide.models.model.location;

public class Location {

    private double longitude;
    private double latitude;

    public Location(final double latitude1,
                    final double longitude1) {
        latitude = latitude1;
        longitude = longitude1;
    }

    public Location() { }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(final double longitude1) {
        longitude = longitude1;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(final double latitude1) {
        latitude = latitude1;
    }
}

