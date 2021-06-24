package org.openclassrooms.tourguide.webapp.service;

import gpsUtil.location.VisitedLocation;

import java.util.List;

public interface TourGuideService {

    List<VisitedLocation> getAllUserCurrentLocations();

    VisitedLocation getUserCurrentLocation(String username);

}
