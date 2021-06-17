package org.openclassrooms.tourguide.localization.service;

import gpsUtil.location.Attraction;

public interface AttractionService {

    Attraction getAttraction(String attractionName);

}
