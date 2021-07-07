package org.openclassrooms.tourguide.webapp.service;

import org.openclassrooms.tourguide.models.model.location.Attraction;
import org.openclassrooms.tourguide.models.model.location.Location;
import org.openclassrooms.tourguide.webapp.exception.ElementNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
    //TODO : check test
    @Override
    public Attraction getAttraction (final String attractionName) {
        LOGGER.info("Getting attraction with name : " + attractionName);
        return webClientGpsApi
                .get()
                .uri("/attractions/" + attractionName)
                .exchangeToMono(clientResponse -> {
                            if(clientResponse.statusCode().equals(HttpStatus.OK)) {
                                return clientResponse.bodyToMono(Attraction.class);
                            } else if(clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
                                throw new ElementNotFoundException("attraction with name : " + attractionName + " not found");
                            } else {
                                return clientResponse.createException()
                                        .flatMap(Mono::error);
                            }
                })
                .block();
    }

    /**
     * @inheritDoc
     */
    //TODO : check test
    @Override
    public List<Attraction> getAllAttractions() {
        LOGGER.info("Getting all referenced attraction");
        return webClientGpsApi
                .get()
                .uri("/attractions")
                .retrieve()
                .bodyToFlux(Attraction.class)
                .collectList()
                .block();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Map<Double, Attraction> getFiveClosestAttractionsWithDistance(final Location userCurrentLocation) {
        TreeMap<Double, Attraction> distanceWithAttraction = new TreeMap<>();
        Map<Double, Attraction> nearestAttractions = new TreeMap<>();

        List<Attraction> allAttractions = getAllAttractions();
        for(Attraction attraction : allAttractions) {
            Double distance = getDistance(userCurrentLocation, attraction);
            distanceWithAttraction.put(distance, attraction);
        }

        for (int i = 0; i < 5; i++) {
            Map.Entry<Double, Attraction> entry  = distanceWithAttraction.pollFirstEntry();
            nearestAttractions.put(entry.getKey(), entry.getValue());
        }
        return nearestAttractions;
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
