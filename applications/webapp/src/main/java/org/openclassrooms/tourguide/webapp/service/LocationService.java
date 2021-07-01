package org.openclassrooms.tourguide.webapp.service;

import org.openclassrooms.tourguide.models.model.location.Attraction;
import org.openclassrooms.tourguide.models.model.location.Location;

import java.util.List;

public interface LocationService {

    /**
     * Get attraction information.
     * @param attractionName of the searched attraction
     * @return attraction - if attraction doesn't exist, throw ElementNotFoundException
     */
    Attraction getAttraction(String attractionName);

    /**
     * Get a list of the five closest attractions from the specified location.
     * @param location specified
     * @return list of the five closest attractions
     */
    List<Attraction> getFiveClosestAttractions(Location location);

    /**
     * Get the distance between an user location and an attraction.
     * @param userLocation of user concerned
     * @param attractionLatitude of attraction concerned
     * @param attractionLongitude of attraction concerned
     * @return distance as double
     */
    double getUserDistanceFromAttraction(Location userLocation, double attractionLatitude, double attractionLongitude);
}
