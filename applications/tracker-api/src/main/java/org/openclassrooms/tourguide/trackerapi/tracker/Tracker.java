package org.openclassrooms.tourguide.trackerapi.tracker;

import org.openclassrooms.tourguide.models.model.user.User;
import org.openclassrooms.tourguide.trackerapi.executor.TrackerExecutor;
import org.openclassrooms.tourguide.trackerapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.time.StopWatch;



import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Tracker implements Runnable {

   private static final Logger LOGGER = LoggerFactory.getLogger(Tracker.class);

    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final AtomicBoolean running = new AtomicBoolean(false);

    private static final long trackingPollingInterval = TimeUnit.MINUTES.toSeconds(5); // TODO : voir pourquoi ne marche pas avec le schedule => parce que doit mettre le schedule dans le main pour initialiser le runnable dans le run

    private final UserService userService;

    private final TrackerExecutor trackerExecutor;

    public Tracker(final UserService userService1, final TrackerExecutor trackerExecutor1) {
        userService = userService1;
        trackerExecutor = trackerExecutor1;
    }

    @Override
    public void run() {
        running.set(true);
        StopWatch stopWatch = new StopWatch();

        while(running.get()) {
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

            try {
                LOGGER.debug("Tracker sleeping");
                TimeUnit.SECONDS.sleep(trackingPollingInterval);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void startTracking() {
        executorService.scheduleAtFixedRate(this, 0, 5, TimeUnit.MINUTES);
    }

    public void stopTracking() {
        running.set(false);
        trackerExecutor.addShutDownHook();
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            LOGGER.debug("Tracker stopping");
            Thread.currentThread().interrupt();
        }
    }
}
