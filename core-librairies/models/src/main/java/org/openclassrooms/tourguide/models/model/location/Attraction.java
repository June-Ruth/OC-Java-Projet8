package org.openclassrooms.tourguide.models.model.location;

import java.util.UUID;

public class Attraction extends Location {

    private String attractionName;
    private String city;
    private String state;
    private UUID attractionId;

    public Attraction(final String attractionName1,
                      final String city1,
                      final String state1,
                      final double latitude1,
                      final double longitude1) {
        super(latitude1, longitude1);
        attractionName = attractionName1;
        city = city1;
        state = state1;
        attractionId = UUID.randomUUID();
    }

    public Attraction() {

    }

    public String getAttractionName() {
        return attractionName;
    }

    public void setAttractionName(final String attractionName1) {
        attractionName = attractionName1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city1) {
        city = city1;
    }

    public String getState() {
        return state;
    }

    public void setState(final String state1) {
        state = state1;
    }

    public UUID getAttractionId() {
        return attractionId;
    }

    public void setAttractionId(final UUID attractionId1) {
        attractionId = attractionId1;
    }
}
