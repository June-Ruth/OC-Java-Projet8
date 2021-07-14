package org.openclassrooms.tourguide.trackerapi.service;

import org.openclassrooms.tourguide.models.model.location.Attraction;
import org.openclassrooms.tourguide.models.model.location.Location;
import org.openclassrooms.tourguide.models.model.location.VisitedLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements LocationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationServiceImpl.class);

    private final WebClient webClientGpsApi;

    public LocationServiceImpl(@Qualifier("getWebClientGpsApi") final WebClient webClientGpsApi1) {
        webClientGpsApi = webClientGpsApi1;
    }

    /**
     * @inheritDoc
     */
    @Override
    public VisitedLocation getUserLocation(final UUID userId) {
        LOGGER.info("Getting user location for user id : " + userId);
        //TODO : IT
        return webClientGpsApi
                .get()
                .uri("/location/" + userId)
                .retrieve()
                .bodyToMono(VisitedLocation.class)
                .block();
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Attraction> getAllAttractions() {
        //TODO : IT
        LOGGER.info("Getting all referenced attraction");
        return webClientGpsApi
                .get()
                .uri("/attractions")
                .retrieve()
                .bodyToFlux(Attraction.class)
                .collectList()
                .block();
    }

    @Override
    public List<Attraction> getAttractionsNearVisitedLocation(VisitedLocation visitedLocation) {
        LOGGER.info("Getting all attractions at proximity.");
        return getAllAttractions()
                .stream()
                .filter(attraction ->
                        isNear(visitedLocation, attraction))
                .collect(Collectors.toList());
    }

    private static final int PROXIMITY_BUFFER = 10;

    private boolean isNear(final VisitedLocation visitedLocation, final Attraction attraction) {
        LOGGER.info("Checking if visited location : " + visitedLocation + " is near of attraction " + attraction);
        return !(getDistance(attraction, visitedLocation.getLocation()) > PROXIMITY_BUFFER);
    }

    private static final double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;

    private double getDistance(Location location1, Location location2) {
        LOGGER.info("Getting distance between " + location1 + " and " + location2);
        double latitude1 = Math.toRadians(location1.getLatitude());
        double longitude1 = Math.toRadians(location1.getLongitude());
        double latitude2 = Math.toRadians(location2.getLatitude());
        double longitude2 = Math.toRadians(location2.getLongitude());
        double angle = Math.acos(Math.sin(latitude1) * Math.sin(latitude2)
                + Math.cos(latitude1) * Math.cos(latitude2) * Math.cos(longitude1 - longitude2));
        double nauticalMiles = 60 * Math.toDegrees(angle);
        return STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
    }
}
