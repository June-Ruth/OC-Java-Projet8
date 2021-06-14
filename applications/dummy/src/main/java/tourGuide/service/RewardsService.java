package tourGuide.service;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import org.openclassrooms.tourguide.models.User;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public interface RewardsService {

    ExecutorService getExecutor();

    void setProximityBuffer(int proximityBuffer);

    CompletableFuture<?> calculateRewards(User user);

    boolean isWithinAttractionProximity(Attraction attraction, Location location);

    double getDistance(Location loc1, Location loc2);
}
