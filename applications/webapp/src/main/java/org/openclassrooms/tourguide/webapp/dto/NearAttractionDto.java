package org.openclassrooms.tourguide.webapp.dto;

public class NearAttractionDto {

    private String attractionName;

    private double attractionLatitude;

    private double attractionLongitude;

    private double userLatitude;

    private double userLongitude;

    /**
     * Distance between the user and the attraction.
     * In Miles.
     */
    private double userDistanceFromLocation;

    private int attractionRewardPoints;

    public NearAttractionDto() { }

    public String getAttractionName() {
        return attractionName;
    }

    public void setAttractionName(final String attractionName1) {
        attractionName = attractionName1;
    }

    public double getAttractionLatitude() {
        return attractionLatitude;
    }

    public void setAttractionLatitude(final double attractionLatitude1) {
        attractionLatitude = attractionLatitude1;
    }

    public double getAttractionLongitude() {
        return attractionLongitude;
    }

    public void setAttractionLongitude(final double attractionLongitude1) {
        attractionLongitude = attractionLongitude1;
    }

    public double getUserLatitude() {
        return userLatitude;
    }

    public void setUserLatitude(final double userLatitude1) {
        userLatitude = userLatitude1;
    }

    public double getUserLongitude() {
        return userLongitude;
    }

    public void setUserLongitude(final double userLongitude1) {
        userLongitude = userLongitude1;
    }

    public double getUserDistanceFromLocation() {
        return userDistanceFromLocation;
    }

    public void setUserDistanceFromLocation(final double userDistanceFromLocation1) {
        userDistanceFromLocation = userDistanceFromLocation1;
    }

    public int getAttractionRewardPoints() {
        return attractionRewardPoints;
    }

    public void setAttractionRewardPoints(final int attractionRewardPoints1) {
        attractionRewardPoints = attractionRewardPoints1;
    }
}
