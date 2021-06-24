package org.openclassrooms.tourguide.attraction.service;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import org.openclassrooms.tourguide.attraction.exception.ElementNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttractionServiceImpl implements AttractionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttractionServiceImpl.class);

    private final GpsUtil gpsUtil;

    public AttractionServiceImpl(final GpsUtil gpsUtil1) {
        gpsUtil = gpsUtil1;
    }

    @Override
    public Attraction getAttraction(final String attractionName) {
        LOGGER.info("Search for attraction with name " + attractionName);
        return gpsUtil.getAttractions().stream()
               .filter(attraction -> attractionName.equals(attraction.attractionName))
               .findAny()
               .orElseThrow(() -> new ElementNotFoundException("Attraction with name : " + attractionName + " was not found."));
    }

    @Override
    public List<Attraction> getFiveNearestAttractions(final Location userCurrentLocation) {
        //TODO
        return null;
    }
}
