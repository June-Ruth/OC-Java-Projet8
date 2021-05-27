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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import rewardCentral.RewardCentral;
import tourGuide.helper.InternalTestHelper;
import tourGuide.service.RewardsService;
import tourGuide.service.RewardsServiceImpl;
import tourGuide.service.TourGuideService;
import tourGuide.service.TourGuideServiceImpl;
import tourGuide.model.User;
import tourGuide.model.UserReward;

public class TestRewardsServiceImpl {

	private RewardsService rewardsService;
	private TourGuideService tourGuideService;
	private GpsUtil gpsUtil;
	private RewardCentral rewardCentral;

	@BeforeAll
	public static void setUp() {
		Locale.setDefault(Locale.US);
	}

	@BeforeEach
	public void beforeEach() {
		gpsUtil = new GpsUtil();
		rewardCentral = new RewardCentral();
		rewardsService = new RewardsServiceImpl(gpsUtil, rewardCentral);
		//tourGuideService = new TourGuideServiceImpl(gpsUtil, rewardsService);
	}

	@Test
	public void userGetRewards() {
		InternalTestHelper.setInternalUserNumber(0);
		TourGuideServiceImpl tourGuideServiceImpl = new TourGuideServiceImpl(gpsUtil, rewardsService);
		
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
		Attraction attraction = gpsUtil.getAttractions().get(0);
		assertTrue(rewardsService.isWithinAttractionProximity(attraction, attraction));
	}
	
	@Disabled // Needs fixed - can throw ConcurrentModificationException
	@Test
	public void nearAllAttractions() {
		rewardsService.setProximityBuffer(Integer.MAX_VALUE);

		InternalTestHelper.setInternalUserNumber(1);
		TourGuideServiceImpl tourGuideServiceImpl = new TourGuideServiceImpl(gpsUtil, rewardsService);
		
		rewardsService.calculateRewards(tourGuideServiceImpl.getAllUsers().get(0));
		List<UserReward> userRewards = tourGuideServiceImpl.getUserRewards(tourGuideServiceImpl.getAllUsers().get(0));
		tourGuideServiceImpl.tracker.stopTracking();

		assertEquals(gpsUtil.getAttractions().size(), userRewards.size());
	}
	
}