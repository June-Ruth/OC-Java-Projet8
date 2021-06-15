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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import rewardCentral.RewardCentral;
import tourGuide.helper.InternalTestHelper;
import tourGuide.service.RewardsService;
import tourGuide.service.RewardsServiceImpl;
import tourGuide.service.TourGuideService;
import tourGuide.service.TourGuideServiceImpl;
import org.openclassrooms.tourguide.models.model.User;
import tripPricer.Provider;

@ActiveProfiles("test")
public class TestTourGuideServiceImpl {

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
	public void getUserLocation() {
		InternalTestHelper.setInternalUserNumber(0);
		tourGuideService = new TourGuideServiceImpl(gpsUtil, rewardsService);
		
		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
		VisitedLocation visitedLocation = tourGuideService.getUserLocation(user);
		assertEquals(visitedLocation.userId, user.getUserId());
	}
	
	@Test
	public void addUser() {
		InternalTestHelper.setInternalUserNumber(0);
		tourGuideService = new TourGuideServiceImpl(gpsUtil, rewardsService);
		
		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
		User user2 = new User(UUID.randomUUID(), "jon2", "000", "jon2@tourGuide.com");

		tourGuideService.addUser(user);
		tourGuideService.addUser(user2);
		
		User retrievedUser = tourGuideService.getUser(user.getUserName());
		User retrievedUser2 = tourGuideService.getUser(user2.getUserName());

		assertEquals(user, retrievedUser);
		assertEquals(user2, retrievedUser2);
	}
	
	@Test
	public void getAllUsers() {
		InternalTestHelper.setInternalUserNumber(0);
		tourGuideService = new TourGuideServiceImpl(gpsUtil, rewardsService);
		
		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
		User user2 = new User(UUID.randomUUID(), "jon2", "000", "jon2@tourGuide.com");

		tourGuideService.addUser(user);
		tourGuideService.addUser(user2);
		
		List<User> allUsers = tourGuideService.getAllUsers();

		assertTrue(allUsers.contains(user));
		assertTrue(allUsers.contains(user2));
	}
	
	@Test
	public void trackUser() {
		InternalTestHelper.setInternalUserNumber(0);
		tourGuideService = new TourGuideServiceImpl(gpsUtil, rewardsService);
		
		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
		VisitedLocation visitedLocation = tourGuideService.getUserLocation(user);

		assertEquals(user.getUserId(), visitedLocation.userId);
	}
	
	@Disabled // Not yet implemented
	@Test
	public void getNearbyAttractions() {
		InternalTestHelper.setInternalUserNumber(0);
		tourGuideService = new TourGuideServiceImpl(gpsUtil, rewardsService);
		
		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
		VisitedLocation visitedLocation = tourGuideService.getUserLocation(user);
		
		List<Attraction> attractions = tourGuideService.getNearByAttractions(visitedLocation);

		assertEquals(5, attractions.size());
	}

	@Test
	public void getTripDeals() {
		InternalTestHelper.setInternalUserNumber(0);
		tourGuideService = new TourGuideServiceImpl(gpsUtil, rewardsService);
		
		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");

		List<Provider> providers = tourGuideService.getTripDeals(user);

		assertEquals(5, providers.size());
	}
	
	
}