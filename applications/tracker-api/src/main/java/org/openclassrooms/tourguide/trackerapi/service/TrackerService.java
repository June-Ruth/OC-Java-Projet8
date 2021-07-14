package org.openclassrooms.tourguide.trackerapi.service;

import org.openclassrooms.tourguide.trackerapi.executor.TrackerExecutor;
import org.openclassrooms.tourguide.trackerapi.tracker.Tracker;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@Service
public class TrackerService {

    private static final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public TrackerService(UserService userService, TrackerExecutor trackerExecutor) {

        final Tracker tracker = new Tracker(userService, trackerExecutor);
        executorService.scheduleAtFixedRate(tracker, 0, 5, TimeUnit.MINUTES);

        Runtime.getRuntime().addShutdownHook(new Thread(TrackerService::stopTracking));
    }

    private static void stopTracking() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
