package org.openclassrooms.tourguide.webapp.service;

import org.openclassrooms.tourguide.models.model.trip.Provider;
import org.openclassrooms.tourguide.models.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class TripsServiceImpl implements TripsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TripsServiceImpl.class);

    private final WebClient webClientTripApi;

    public TripsServiceImpl(@Qualifier("getWebClientTripApi") WebClient webClientTripApi1) {
        webClientTripApi =webClientTripApi1;
    }

    /**
     * @inheritDoc
     */
    //TODO : check test
    @Override
    public List<Provider> getTripDeals(User user) {
        LOGGER.info("Getting trip deals for user " + user.getUsername());
        List<Provider> providers = new ArrayList<>();
        return webClientTripApi
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/trips/" + user.getUsername())
                        .queryParam("user", user)
                        .build())
                .retrieve()
                .bodyToFlux(Provider.class)
                .collectList()
                .block();
    }
}
