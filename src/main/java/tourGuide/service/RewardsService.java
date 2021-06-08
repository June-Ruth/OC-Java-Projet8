package tourGuide.service;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import tourGuide.model.User;

import java.util.concurrent.CompletableFuture;

public interface RewardsService {

    void setProximityBuffer(int proximityBuffer);

    CompletableFuture<?> calculateRewards(User user);

    boolean isWithinAttractionProximity(Attraction attraction, Location location);

    double getDistance(Location loc1, Location loc2);
}
