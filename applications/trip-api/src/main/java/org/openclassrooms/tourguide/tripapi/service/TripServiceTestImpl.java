package org.openclassrooms.tourguide.tripapi.service;

import org.openclassrooms.tourguide.models.model.trip.Provider;
import org.openclassrooms.tourguide.models.model.user.User;
import org.openclassrooms.tourguide.models.model.user.UserReward;
import org.openclassrooms.tourguide.tripapi.util.FromLibraryToModelConvertor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import tripPricer.TripPricer;

import java.util.List;
import java.util.stream.Collectors;

@Profile("test")
@Service
public class TripServiceTestImpl implements TripService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TripServiceTestImpl.class);

    private final TripPricer tripPricer = new TripPricer(); //TODO : voir si possible de le caler en Bean pour faire plus propre ou si au contraire c'est mieux comme ça

    public TripServiceTestImpl() { }

    private static final String tripPricerApiKey = "test-server-api-key";

    /**
     * @inheritDoc
     */
    @Override
    public List<Provider> getTripDeals(User user) {

        LOGGER.info("Getting trip deals for user : " + user.getUsername());

        int cumulativeRewardPoints = user.getUserRewards()
                .stream()
                .mapToInt(UserReward::getRewardPoints)
                .sum();

        List<tripPricer.Provider> libraryProviders = tripPricer.getPrice(tripPricerApiKey,
                user.getUserId(),
                user.getUserPreferences().getNumberOfAdults(),
                user.getUserPreferences().getNumberOfChildren(),
                user.getUserPreferences().getTripDuration(),
                cumulativeRewardPoints);

        return libraryProviders.stream().map(FromLibraryToModelConvertor::convertProvider).collect(Collectors.toList());
    }



}
