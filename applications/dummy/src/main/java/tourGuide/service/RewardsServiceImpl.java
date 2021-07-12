package tourGuide.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import rewardCentral.RewardCentral;
import org.openclassrooms.tourguide.models.model.user.User;
import org.openclassrooms.tourguide.models.model.user.UserReward;

@Service
public class RewardsServiceImpl /* implements RewardsService */ {
  /*

	// proximity in miles
	private int attractionProximityRange = 200;
	private final GpsUtil gpsUtil;
	private final RewardCentral rewardsCentral;


	@Override
	public ExecutorService getExecutor() {
		return executor;
	}

	public RewardsServiceImpl(GpsUtil gpsUtil, RewardCentral rewardCentral) {
		this.gpsUtil = gpsUtil;
		this.rewardsCentral = rewardCentral;
	}

	@Override
	public boolean isWithinAttractionProximity(Attraction attraction, Location location) {
		return !(getDistance(attraction, location) > attractionProximityRange);
	}*/
}
