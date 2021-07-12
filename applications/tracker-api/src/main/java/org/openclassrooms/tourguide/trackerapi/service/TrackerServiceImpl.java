package org.openclassrooms.tourguide.trackerapi.service;

import org.openclassrooms.tourguide.models.model.location.VisitedLocation;
import org.openclassrooms.tourguide.models.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class TrackerServiceImpl implements TrackerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrackerServiceImpl.class);

    private final LocationService locationService;
    private final UserService userService;
    private final RewardService rewardService;

    ExecutorService executor = Executors.newFixedThreadPool(800);

    public TrackerServiceImpl(final LocationService locationService1,
                              final UserService userService1,
                              final RewardService rewardService1) {
        locationService = locationService1;
        userService = userService1;
        rewardService = rewardService1;
    }

    /**
     * @inheritDoc
     */
    @Override
    public CompletableFuture<?> trackUserLocation(User user) {

        return CompletableFuture.supplyAsync(() -> locationService.getUserLocation(user.getUserId()), executor)
                .thenAccept(visitedLocation -> addToVisitedLocationsOfUser(visitedLocation, user))
                .thenRunAsync(() -> rewardService.calculateRewards(user));
    }

    private VisitedLocation addToVisitedLocationsOfUser(VisitedLocation visitedLocation, User user) {
        List<VisitedLocation> visitedLocations = user.getVisitedLocations();
        visitedLocations.add(visitedLocation);
        return visitedLocation;
    }
}
