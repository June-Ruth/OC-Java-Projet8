package tourGuide.tracker;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tourGuide.model.User;
import tourGuide.service.TourGuideService;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Tracker implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(Tracker.class);

    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final AtomicBoolean running = new AtomicBoolean(false);

    private final TourGuideService tourGuideService;

    public Tracker(final TourGuideService tourGuideService1) {
        tourGuideService = tourGuideService1;
    }

    public void startTracking() {
        executorService.scheduleAtFixedRate(this, 0, 5, TimeUnit.MINUTES);
    }

    public void stopTracking() {
        running.set(false);
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        } finally {
            LOGGER.debug("Tracker stopping");

        }
    }

    @Override
    public void run() {
        running.set(true);
        StopWatch stopWatch = new StopWatch();
        while(running.get()) {
            List<User> users = tourGuideService.getAllUsers();
            LOGGER.debug("Begin Tracker. Tracking " + users.size() + " users.");
            stopWatch.start();
            users.forEach(tourGuideService::trackUserLocation); //synchronized?
            stopWatch.stop();
            LOGGER.debug("Tracker Time Elapsed: "
                    + TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds.");
            stopWatch.reset();
        }
    }


}
