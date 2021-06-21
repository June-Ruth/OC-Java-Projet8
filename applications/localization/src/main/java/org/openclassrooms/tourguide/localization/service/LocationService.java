package org.openclassrooms.tourguide.localization.service;

import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;

import java.util.List;

public interface LocationService {

    // User Location

    VisitedLocation getUserCurrentLocation(String username);

    List<VisitedLocation> getAllUserCurrentLocations();

    // Attraction

    Attraction getAttraction(String attractionName);

    List<Attraction> getFiveNearestAttractions(VisitedLocation visitedLocation);

    int getAttractionRewardPoints(Attraction attraction);
}
