package tourGuide.service;

import java.util.ArrayList;
import java.util.List;

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
	public void calculateRewards(User user) {
		List<VisitedLocation> userLocations = new ArrayList<>(user.getVisitedLocations());
		List<Attraction> attractions = gpsUtil.getAttractions();
		List<UserReward> userRewards = new ArrayList<>(user.getUserRewards());

		for(VisitedLocation visitedLocation : userLocations) {
			for(Attraction attraction : attractions) {
				if(userRewards.stream().noneMatch(r -> r.getAttraction().attractionName.equals(attraction.attractionName))) {
					if(nearAttraction(visitedLocation, attraction)) {
						addUserRewardToUser(new UserReward(visitedLocation, attraction, getRewardPoints(attraction, user)), user);
					}
				}
			}
		}
	}

	public void addUserRewardToUser(UserReward userReward, User user) {
		List<UserReward> userRewards = user.getUserRewards();
		if(userRewards.stream().noneMatch(r -> !r.getAttraction().attractionName.equals(userReward.getAttraction().attractionName))) {
			userRewards.add(userReward);
		}
	}

	@Override
	public boolean isWithinAttractionProximity(Attraction attraction, Location location) {
		return !(getDistance(attraction, location) > attractionProximityRange);
	}
	
	private boolean nearAttraction(VisitedLocation visitedLocation, Attraction attraction) {
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
