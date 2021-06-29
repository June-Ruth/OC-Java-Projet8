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
}
