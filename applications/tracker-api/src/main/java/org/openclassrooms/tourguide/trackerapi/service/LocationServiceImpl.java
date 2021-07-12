package org.openclassrooms.tourguide.trackerapi.service;

import org.openclassrooms.tourguide.models.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.CompletableFuture;

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
    public CompletableFuture<?> trackUserLocation(User user) {
        return null;
    }
}
