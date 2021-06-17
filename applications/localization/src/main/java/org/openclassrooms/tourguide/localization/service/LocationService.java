package org.openclassrooms.tourguide.localization.service;

import gpsUtil.location.VisitedLocation;

public interface LocationService {

    VisitedLocation getUserCurrentLocation(String username);

}
