package tourGuide.tracker;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tourGuide.model.User;
import tourGuide.service.TourGuideService;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Tracker implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(Tracker.class);

    private final TourGuideService tourGuideService;

    private static final long trackingPollingInterval = TimeUnit.MINUTES.toSeconds(5);
    private boolean stop = false;

    public Tracker(final TourGuideService tourGuideService1) {
        tourGuideService = tourGuideService1;
    }

    @Override
    public void run() {
        StopWatch stopWatch = new StopWatch();
        while(true) {
            if(Thread.currentThread().isInterrupted() || stop) {
                LOGGER.debug("Tracker stopping");
                break;
            }

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

    public void stopTracking() {
        stop = true;
    }
}
