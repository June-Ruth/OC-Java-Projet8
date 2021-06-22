package org.openclassrooms.tourguide.attraction.service;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;

import java.util.List;

public interface AttractionService {

    // Attraction

    Attraction getAttraction(String attractionName);

    List<Attraction> getFiveNearestAttractions(Location location);

    int getAttractionRewardPoints(Attraction attraction);
}
