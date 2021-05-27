package tourGuide;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import rewardCentral.RewardCentral;
import tourGuide.helper.InternalTestHelper;
import tourGuide.service.RewardsServiceImpl;
import tourGuide.service.TourGuideServiceImpl;
import tourGuide.user.User;
import tourGuide.user.UserReward;

public class TestRewardsServiceImpl {

	@BeforeAll
	public static void setUp() {
		Locale.setDefault(Locale.US);
	}

	@Test
	public void userGetRewards() {
		GpsUtil gpsUtil = new GpsUtil();
		RewardsServiceImpl rewardsServiceImpl = new RewardsServiceImpl(gpsUtil, new RewardCentral());

		InternalTestHelper.setInternalUserNumber(0);
		TourGuideServiceImpl tourGuideServiceImpl = new TourGuideServiceImpl(gpsUtil, rewardsServiceImpl);
		
		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
		Attraction attraction = gpsUtil.getAttractions().get(0);
		user.addToVisitedLocations(new VisitedLocation(user.getUserId(), attraction, new Date()));
		tourGuideServiceImpl.trackUserLocation(user);
		List<UserReward> userRewards = user.getUserRewards();
		tourGuideServiceImpl.tracker.stopTracking();
		assertEquals(1, userRewards.size());
	}
	
	@Test
	public void isWithinAttractionProximity() {
		GpsUtil gpsUtil = new GpsUtil();
		RewardsServiceImpl rewardsServiceImpl = new RewardsServiceImpl(gpsUtil, new RewardCentral());
		Attraction attraction = gpsUtil.getAttractions().get(0);
		assertTrue(rewardsServiceImpl.isWithinAttractionProximity(attraction, attraction));
	}
	
	@Disabled // Needs fixed - can throw ConcurrentModificationException
	@Test
	public void nearAllAttractions() {
		GpsUtil gpsUtil = new GpsUtil();
		RewardsServiceImpl rewardsServiceImpl = new RewardsServiceImpl(gpsUtil, new RewardCentral());
		rewardsServiceImpl.setProximityBuffer(Integer.MAX_VALUE);

		InternalTestHelper.setInternalUserNumber(1);
		TourGuideServiceImpl tourGuideServiceImpl = new TourGuideServiceImpl(gpsUtil, rewardsServiceImpl);
		
		rewardsServiceImpl.calculateRewards(tourGuideServiceImpl.getAllUsers().get(0));
		List<UserReward> userRewards = tourGuideServiceImpl.getUserRewards(tourGuideServiceImpl.getAllUsers().get(0));
		tourGuideServiceImpl.tracker.stopTracking();

		assertEquals(gpsUtil.getAttractions().size(), userRewards.size());
	}
	
}
