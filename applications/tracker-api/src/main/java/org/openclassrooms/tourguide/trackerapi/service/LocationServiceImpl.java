package org.openclassrooms.tourguide.trackerapi.service;

import org.openclassrooms.tourguide.models.model.location.Attraction;
import org.openclassrooms.tourguide.models.model.location.VisitedLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

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
        LOGGER.info("Getting all referenced attraction");
        return webClientGpsApi
                .get()
                .uri("/attractions")
                .retrieve()
                .bodyToFlux(Attraction.class)
                .collectList()
                .block();
    }
}
