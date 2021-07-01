package tourGuide;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.springframework.test.context.ActiveProfiles;

@Disabled
@ActiveProfiles("test")
public class TestTourGuideServiceImpl {
/*
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
	public void trackUser() {
		//InternalTestHelper.setInternalUserNumber(0);
		tourGuideService = new TourGuideServiceImpl(gpsUtil, rewardsService);
		
		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
		VisitedLocation visitedLocation = tourGuideService.getUserLocation(user);

		assertEquals(user.getUserId(), visitedLocation.userId);
	}
	
	@Disabled // Not yet implemented
	@Test
	public void getNearbyAttractions() {
		//InternalTestHelper.setInternalUserNumber(0);
		tourGuideService = new TourGuideServiceImpl(gpsUtil, rewardsService);
		
		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
		VisitedLocation visitedLocation = tourGuideService.getUserLocation(user);
		
		List<Attraction> attractions = tourGuideService.getNearByAttractions(visitedLocation);

		assertEquals(5, attractions.size());
	}
	*/
	
}
