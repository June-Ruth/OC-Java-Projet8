package org.openclassrooms.tourguide.tripapi.service;

import org.openclassrooms.tourguide.models.model.trip.Provider;
import org.openclassrooms.tourguide.tripapi.util.FromLibraryToModelConvertor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tripPricer.TripPricer;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TripServiceTestImpl implements TripService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TripServiceTestImpl.class);

    private final TripPricer tripPricer = new TripPricer();

    public TripServiceTestImpl() { }

    private static final String tripPricerApiKey = "test-server-api-key";

    /**
     * @inheritDoc
     */
    @Override
    public List<Provider> getTripDeals(final UUID userId,
                                       final int numberOfAdults,
                                       final int numberOfChildren,
                                       final int tripDuration,
                                       final int cumulativeRewardPoints) {

        LOGGER.info("Getting trip deals for user : " + userId);


        return tripPricer.getPrice(tripPricerApiKey,
                userId,
                numberOfAdults,
                numberOfChildren,
                tripDuration,
                cumulativeRewardPoints)
                .stream()
                .map(FromLibraryToModelConvertor::convertProvider)
                .collect(Collectors.toList());
    }
}
