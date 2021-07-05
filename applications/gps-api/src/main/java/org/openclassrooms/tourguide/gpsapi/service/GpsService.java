package org.openclassrooms.tourguide.gpsapi.service;

import org.openclassrooms.tourguide.models.model.location.Attraction;

import java.util.List;

public interface GpsService {

    /**
     * Access to attraction information.
     * @param attractionName of the searched attraction
     * @return attraction - if attraction doesn't exist, throw ElementNotFoundException
     */
    Attraction getAttraction(String attractionName);

    /**
     * Get all attractions.
     * @return list of all attractions
     */
    List<Attraction> getAllAttractions();

}
