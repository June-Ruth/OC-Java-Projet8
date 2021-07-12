package org.openclassrooms.tourguide.trackerapi.service;

import org.openclassrooms.tourguide.models.model.location.Attraction;
import org.openclassrooms.tourguide.models.model.location.VisitedLocation;

import java.util.List;
import java.util.UUID;

public interface LocationService {

    VisitedLocation getUserLocation(final UUID userId);

    /**
     * Get all the referenced attractions.
     * @return list of all attractions
     */
    List<Attraction> getAllAttractions();
}
