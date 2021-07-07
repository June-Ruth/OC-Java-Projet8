package org.openclassrooms.tourguide.models.model.trip;

import java.util.UUID;

public class Provider {

    private UUID tripId;
    private String name;
    private double price;

    public Provider(final UUID tripId1,
                    final String name1,
                    final double price1) {
        name = name1;
        price = price1;
        tripId = tripId1;
    }

    public Provider() { }

    public UUID getTripId() {
        return tripId;
    }

    public void setTripId(final UUID tripId1) {
        tripId = tripId1;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name1) {
        name = name1;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double price1) {
        price = price1;
    }
}
