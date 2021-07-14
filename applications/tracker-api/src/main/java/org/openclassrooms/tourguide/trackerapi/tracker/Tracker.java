package org.openclassrooms.tourguide.trackerapi.tracker;

import org.openclassrooms.tourguide.models.model.user.User;
import org.openclassrooms.tourguide.trackerapi.executor.TrackerExecutor;
import org.openclassrooms.tourguide.trackerapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.time.StopWatch;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Tracker implements Runnable {

   private static final Logger LOGGER = LoggerFactory.getLogger(Tracker.class);

    private final UserService userService;
    private final TrackerExecutor trackerExecutor;

    public Tracker(final UserService userService1, final TrackerExecutor trackerExecutor1) {
        userService = userService1;
        trackerExecutor = trackerExecutor1;
    }

    @Override
    public void run() {
        StopWatch stopWatch = new StopWatch();
        List<User> users = userService.getAllUsers();

        LOGGER.debug("Begin Tracker. Tracking " + users.size() + " users.");

        stopWatch.start();

        CompletableFuture<?>[] completableFutures = users.stream()
                .map(trackerExecutor::trackUserLocation)
                .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(completableFutures)
                .join();

        stopWatch.stop();

        LOGGER.debug("Tracker Time Elapsed: "
                + TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds.");
        stopWatch.reset();

        trackerExecutor.addShutDownHook();
    }
}
