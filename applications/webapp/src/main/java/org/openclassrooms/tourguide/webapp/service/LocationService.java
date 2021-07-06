package org.openclassrooms.tourguide.webapp.service;

import org.openclassrooms.tourguide.models.model.location.Attraction;
import org.openclassrooms.tourguide.models.model.location.Location;

import java.util.List;
import java.util.Map;

public interface LocationService {

    /**
     * Get attraction information.
     * @param attractionName of the searched attraction
     * @return attraction - if attraction doesn't exist, throw ElementNotFoundException
     */
    Attraction getAttraction(String attractionName);

    /**
     * Get all the referenced attractions.
     * @return list of all attractions
     */
    List<Attraction> getAllAttractions();

    /**
     * Get a list of the five closest attractions from the specified location with their distance.
     * @param location specified
     * @return map of the five closest attractions as value and the distance as key
     */
    Map<Double, Attraction> getFiveClosestAttractionsWithDistance(Location location);
}
