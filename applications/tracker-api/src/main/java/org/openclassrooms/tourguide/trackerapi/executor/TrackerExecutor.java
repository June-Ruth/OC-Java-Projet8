package org.openclassrooms.tourguide.trackerapi.executor;

import org.openclassrooms.tourguide.models.model.user.User;
import org.openclassrooms.tourguide.trackerapi.service.LocationService;
import org.openclassrooms.tourguide.trackerapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//TODO : voir si doit rester un service
public class TrackerExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrackerExecutor.class);

    private final LocationService locationService;
    private final UserService userService;
    private final RewardExecutor rewardExecutor;

    private final ExecutorService executor;

    public TrackerExecutor(final LocationService locationService1,
                           final UserService userService1,
                           final RewardExecutor rewardExecutor1) {
        locationService = locationService1;
        userService = userService1;
        rewardExecutor = rewardExecutor1;
        executor = Executors.newFixedThreadPool(800);
    }

    //@Override
    public CompletableFuture<?> trackUserLocation(User user) {
        //TODO : unit test
        LOGGER.info("Tracking user location for user " + user);
        return CompletableFuture.supplyAsync(() -> locationService.getUserLocation(user.getUserId()), executor)
                .thenAccept(visitedLocation -> userService.addToVisitedLocationsOfUser(visitedLocation, user))
                .thenRunAsync(() -> rewardExecutor.calculateRewards(user));
    }

    //@Override
    //TODO : for testing only, to clean or see how to manage
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
