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
import java.util.concurrent.TimeUnit;

@Service
public class TrackerServiceImpl implements TrackerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrackerServiceImpl.class);

    private final LocationService locationService;
    private final RewardService rewardService;

    private final ExecutorService executor;

    public TrackerServiceImpl(final LocationService locationService1,
                              final RewardService rewardService1) {
        locationService = locationService1;
        rewardService = rewardService1;
        executor = Executors.newFixedThreadPool(800);
    }

    /**
     * @inheritDoc
     */
    @Override
    public CompletableFuture<?> trackUserLocation(User user) {
        LOGGER.info("Tracking user location for user " + user);
        return CompletableFuture.supplyAsync(() -> locationService.getUserLocation(user.getUserId()), executor)
                .thenAccept(visitedLocation -> addToVisitedLocationsOfUser(visitedLocation, user))
                .thenRunAsync(() -> rewardService.calculateRewards(user));
    }

    @Override
    public VisitedLocation addToVisitedLocationsOfUser(VisitedLocation visitedLocation, User user) {
        List<VisitedLocation> visitedLocations = user.getVisitedLocations();
        visitedLocations.add(visitedLocation);
        return visitedLocation;
    }

    @Override
    //TODO : for testing only, to clean
    public void addShutDownHook() {
        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
