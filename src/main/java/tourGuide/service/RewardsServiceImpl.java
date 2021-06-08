package tourGuide.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import rewardCentral.RewardCentral;
import tourGuide.model.User;
import tourGuide.model.UserReward;

@Service
public class RewardsServiceImpl implements RewardsService {
    private static final double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;

	// proximity in miles
    private int defaultProximityBuffer = 10;
	private int proximityBuffer = defaultProximityBuffer;
	private int attractionProximityRange = 200;
	private final GpsUtil gpsUtil;
	private final RewardCentral rewardsCentral;

	//Executor executor = Executors.newFixedThreadPool(2000);

	public RewardsServiceImpl(GpsUtil gpsUtil, RewardCentral rewardCentral) {
		this.gpsUtil = gpsUtil;
		this.rewardsCentral = rewardCentral;
	}

	@Override
	public void setProximityBuffer(int proximityBuffer) {
		this.proximityBuffer = proximityBuffer;
	}

	public void setDefaultProximityBuffer() {
		proximityBuffer = defaultProximityBuffer;
	}

	@Override
	public CompletableFuture<?> calculateRewards(User user) {
		List<UserReward> userRewards = new ArrayList<>(user.getUserRewards());
		return CompletableFuture.runAsync(() -> new ArrayList<>(user.getVisitedLocations()).forEach(
				userLocation -> getAttractionsNearVisitedLocation(userLocation).forEach(attraction -> {
							if(isAttractionNotAlreadyInUserRewards(attraction, userRewards)) {
								addUserRewardToUser(new UserReward(userLocation, attraction, getRewardPoints(attraction, user)), user);
					}

				}
				)) /*,executor*/);
	}

	public void addUserRewardToUser(UserReward userReward, User user) {
		List<UserReward> userRewards = user.getUserRewards();
		if(userRewards.stream().noneMatch(reward -> reward.getAttraction().attractionName.equals(userReward.getAttraction().attractionName))) {
			userRewards.add(userReward);
		}
	}

	@Override
	public boolean isWithinAttractionProximity(Attraction attraction, Location location) {
		return !(getDistance(attraction, location) > attractionProximityRange);
	}

	private boolean isAttractionNotAlreadyInUserRewards(Attraction attraction, List<UserReward> userRewards) {
		return userRewards.stream().noneMatch(userReward -> userReward.getAttraction().attractionName
				.equals(attraction.attractionName));
	}

	private List<Attraction> getAttractionsNearVisitedLocation(VisitedLocation visitedLocation) {
		List<Attraction> attractions = gpsUtil.getAttractions();
		List<Attraction> attractionsNearVisitedLocation = attractions.stream().filter(attraction -> isNear(visitedLocation, attraction)).collect(Collectors.toList());

		return attractionsNearVisitedLocation;
	}
	
	private boolean isNear(VisitedLocation visitedLocation, Attraction attraction) {
		return !(getDistance(attraction, visitedLocation.location) > proximityBuffer);
	}
	
	private int getRewardPoints(Attraction attraction, User user) {
		return rewardsCentral.getAttractionRewardPoints(attraction.attractionId, user.getUserId());
	}

	@Override
	public double getDistance(Location loc1, Location loc2) {
        double lat1 = Math.toRadians(loc1.latitude);
        double lon1 = Math.toRadians(loc1.longitude);
        double lat2 = Math.toRadians(loc2.latitude);
        double lon2 = Math.toRadians(loc2.longitude);

        double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
                               + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

        double nauticalMiles = 60 * Math.toDegrees(angle);
        double statuteMiles = STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
        return statuteMiles;
	}

}
