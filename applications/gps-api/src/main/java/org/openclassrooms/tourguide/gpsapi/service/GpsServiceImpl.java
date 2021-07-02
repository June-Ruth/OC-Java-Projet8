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
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

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
    public Map<Double, Attraction> getFiveNearestAttractions(final Location userCurrentLocation) {
        LOGGER.info("Getting the five nearest attractions from " + userCurrentLocation);

        TreeMap<Double, Attraction> distanceWithAttraction = new TreeMap<>();
        Map<Double, Attraction> nearestAttractions = new TreeMap<>();

        List<Attraction> allAttractions = getAllAttractions();
        for(Attraction attraction : allAttractions) {
            Double distance = getDistance(userCurrentLocation, attraction);
            distanceWithAttraction.put(distance, attraction);
        }

        int i = 0;
        while (i < 5) {
            i++;
            Map.Entry<Double, Attraction> entry  = distanceWithAttraction.pollFirstEntry();
            nearestAttractions.put(entry.getKey(), entry.getValue());
        }
        return nearestAttractions;
    }

    /**
     * Get all the referenced attractions.
     * @return list of all attractions
     */
    private List<Attraction> getAllAttractions() { //TODO : tests
        LOGGER.info("Getting all referenced attraction");
        return gpsUtil.getAttractions()
                .stream()
                .map(FromLibraryToModelConvertor::convertAttraction)
                .collect(Collectors.toList());
    }

     private static final double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;

    /**
     * Get the distance between two locations.
     * @param location1 to compare
     * @param location2 to compare
     * @return distance between in miles as double
     */
    private double getDistance(Location location1, Location location2) {
        LOGGER.info("Getting distance between " + location1 + " and " + location2);
        double lat1 = Math.toRadians(location1.getLatitude());
        double lon1 = Math.toRadians(location1.getLongitude());
        double lat2 = Math.toRadians(location2.getLatitude());
        double lon2 = Math.toRadians(location2.getLongitude());

        double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

        double nauticalMiles = 60 * Math.toDegrees(angle);
        double statuteMiles = STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
        return statuteMiles;
    }
}
