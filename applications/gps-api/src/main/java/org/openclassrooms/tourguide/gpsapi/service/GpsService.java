package org.openclassrooms.tourguide.gpsapi.service;

import org.openclassrooms.tourguide.models.model.location.Attraction;
import org.openclassrooms.tourguide.models.model.location.Location;

import java.util.List;

public interface GpsService {

    /**
     * Access to attraction information.
     * @param attractionName of the searched attraction
     * @return attraction - if attraction doesn't exist, throw ElementNotFoundException
     */
    Attraction getAttraction(String attractionName);

    /**
     * Get the closest five attractions to the userLocation, no matter how far away they are.
     * @param location of the user
     * @return list of attraction
     */
    List<Attraction> getFiveNearestAttractions(Location location);

}
