package tourGuide;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import tripPricer.Provider;

public class TestTourGuideServiceImpl {

	@BeforeAll
	public static void setUp() {
		Locale.setDefault(Locale.US);
	}

	@Test
	public void getUserLocation() {
		GpsUtil gpsUtil = new GpsUtil();
		RewardsServiceImpl rewardsServiceImpl = new RewardsServiceImpl(gpsUtil, new RewardCentral());
		InternalTestHelper.setInternalUserNumber(0);
		TourGuideServiceImpl tourGuideServiceImpl = new TourGuideServiceImpl(gpsUtil, rewardsServiceImpl);
		
		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
		VisitedLocation visitedLocation = tourGuideServiceImpl.trackUserLocation(user);
		tourGuideServiceImpl.tracker.stopTracking();
		assertEquals(visitedLocation.userId, user.getUserId());
	}
	
	@Test
	public void addUser() {
		GpsUtil gpsUtil = new GpsUtil();
		RewardsServiceImpl rewardsServiceImpl = new RewardsServiceImpl(gpsUtil, new RewardCentral());
		InternalTestHelper.setInternalUserNumber(0);
		TourGuideServiceImpl tourGuideServiceImpl = new TourGuideServiceImpl(gpsUtil, rewardsServiceImpl);
		
		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
		User user2 = new User(UUID.randomUUID(), "jon2", "000", "jon2@tourGuide.com");

		tourGuideServiceImpl.addUser(user);
		tourGuideServiceImpl.addUser(user2);
		
		User retrivedUser = tourGuideServiceImpl.getUser(user.getUserName());
		User retrivedUser2 = tourGuideServiceImpl.getUser(user2.getUserName());

		tourGuideServiceImpl.tracker.stopTracking();
		
		assertEquals(user, retrivedUser);
		assertEquals(user2, retrivedUser2);
	}
	
	@Test
	public void getAllUsers() {
		GpsUtil gpsUtil = new GpsUtil();
		RewardsServiceImpl rewardsServiceImpl = new RewardsServiceImpl(gpsUtil, new RewardCentral());
		InternalTestHelper.setInternalUserNumber(0);
		TourGuideServiceImpl tourGuideServiceImpl = new TourGuideServiceImpl(gpsUtil, rewardsServiceImpl);
		
		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
		User user2 = new User(UUID.randomUUID(), "jon2", "000", "jon2@tourGuide.com");

		tourGuideServiceImpl.addUser(user);
		tourGuideServiceImpl.addUser(user2);
		
		List<User> allUsers = tourGuideServiceImpl.getAllUsers();

		tourGuideServiceImpl.tracker.stopTracking();
		
		assertTrue(allUsers.contains(user));
		assertTrue(allUsers.contains(user2));
	}
	
	@Test
	public void trackUser() {
		GpsUtil gpsUtil = new GpsUtil();
		RewardsServiceImpl rewardsServiceImpl = new RewardsServiceImpl(gpsUtil, new RewardCentral());
		InternalTestHelper.setInternalUserNumber(0);
		TourGuideServiceImpl tourGuideServiceImpl = new TourGuideServiceImpl(gpsUtil, rewardsServiceImpl);
		
		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
		VisitedLocation visitedLocation = tourGuideServiceImpl.trackUserLocation(user);
		
		tourGuideServiceImpl.tracker.stopTracking();
		
		assertEquals(user.getUserId(), visitedLocation.userId);
	}
	
	@Disabled // Not yet implemented
	@Test
	public void getNearbyAttractions() {
		GpsUtil gpsUtil = new GpsUtil();
		RewardsServiceImpl rewardsServiceImpl = new RewardsServiceImpl(gpsUtil, new RewardCentral());
		InternalTestHelper.setInternalUserNumber(0);
		TourGuideServiceImpl tourGuideServiceImpl = new TourGuideServiceImpl(gpsUtil, rewardsServiceImpl);
		
		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
		VisitedLocation visitedLocation = tourGuideServiceImpl.trackUserLocation(user);
		
		List<Attraction> attractions = tourGuideServiceImpl.getNearByAttractions(visitedLocation);
		
		tourGuideServiceImpl.tracker.stopTracking();
		
		assertEquals(5, attractions.size());
	}

	@Disabled // To fix
	@Test
	public void getTripDeals() {
		GpsUtil gpsUtil = new GpsUtil();
		RewardsServiceImpl rewardsServiceImpl = new RewardsServiceImpl(gpsUtil, new RewardCentral());
		InternalTestHelper.setInternalUserNumber(0);
		TourGuideServiceImpl tourGuideServiceImpl = new TourGuideServiceImpl(gpsUtil, rewardsServiceImpl);
		
		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");

		List<Provider> providers = tourGuideServiceImpl.getTripDeals(user);
		
		tourGuideServiceImpl.tracker.stopTracking();
		
		assertEquals(10, providers.size());
	}
	
	
}
