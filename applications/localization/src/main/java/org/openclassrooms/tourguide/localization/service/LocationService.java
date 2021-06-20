package org.openclassrooms.tourguide.localization.service;

import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;

import java.util.List;

public interface LocationService {

    VisitedLocation getUserCurrentLocation(String username);


    Attraction getAttraction(String attractionName);

    List<Attraction> getFiveNearestAttractions(VisitedLocation visitedLocation);

    int getAttractionRewardPoints(Attraction attraction);
}
