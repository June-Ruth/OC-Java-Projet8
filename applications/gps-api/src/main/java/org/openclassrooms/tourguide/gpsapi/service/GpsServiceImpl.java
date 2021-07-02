package org.openclassrooms.tourguide.gpsapi.service;

import gpsUtil.GpsUtil;
import org.openclassrooms.tourguide.gpsapi.exception.ElementNotFoundException;
import org.openclassrooms.tourguide.gpsapi.util.FromLibraryToModelConvertor;
import org.openclassrooms.tourguide.models.model.location.Attraction;
import org.openclassrooms.tourguide.models.model.location.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GpsServiceImpl implements GpsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GpsServiceImpl.class);

    private final GpsUtil gpsUtil;

    public GpsServiceImpl(final GpsUtil gpsUtil1) {
        gpsUtil = gpsUtil1;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Attraction getAttraction(final String attractionName) {
        LOGGER.info("Search for attraction with name " + attractionName);
        return FromLibraryToModelConvertor.convertAttraction(gpsUtil.getAttractions().stream()
                .filter(attraction -> attractionName.equals(attraction.attractionName))
                .findAny()
                .orElseThrow(() -> new ElementNotFoundException("Attraction with name : " + attractionName + " was not found.")));
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Attraction> getFiveNearestAttractions(final Location userCurrentLocation) {
        //TODO
        return null;
    }
}
