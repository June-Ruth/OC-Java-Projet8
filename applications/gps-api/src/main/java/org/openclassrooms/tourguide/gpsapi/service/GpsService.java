package org.openclassrooms.tourguide.gpsapi.service;

import org.openclassrooms.tourguide.models.model.location.Attraction;
import org.openclassrooms.tourguide.models.model.location.VisitedLocation;

import java.util.List;
import java.util.UUID;

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

    /**
     * Get actual user location with gps.
     * @param userId of searched user
     * @return visited location
     */
    VisitedLocation getUserActualLocation(final UUID userId);

}
