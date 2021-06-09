package tourGuide;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.StopWatch;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import rewardCentral.RewardCentral;
import tourGuide.helper.InternalTestHelper;
import tourGuide.service.RewardsService;
import tourGuide.service.RewardsServiceImpl;
import tourGuide.service.TourGuideService;
import tourGuide.service.TourGuideServiceImpl;
import tourGuide.model.User;

import static org.junit.jupiter.api.Assertions.assertTrue;

//@Disabled
@ActiveProfiles("test")
public class PerformanceTest {

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

	/*
	 * A note on performance improvements:
	 *     
	 *     The number of users generated for the high volume tests can be easily adjusted via this method:
	 *     
	 *     		InternalTestHelper.setInternalUserNumber(100000);
	 *     
	 *     
	 *     These tests can be modified to suit new solutions, just as long as the performance metrics
	 *     at the end of the tests remains consistent. 
	 * 
	 *     These are performance metrics that we are trying to hit:
	 *     
	 *     highVolumeTrackLocation: 100,000 users within 15 minutes:
	 *     		assertTrue(TimeUnit.MINUTES.toSeconds(15) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
     *
     *     highVolumeGetRewards: 100,000 users within 20 minutes:
	 *          assertTrue(TimeUnit.MINUTES.toSeconds(20) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
	 */

	// TRACK LOCATION PERFORMANCE TESTS //

	private void trackLocationWithXUsersModel(int userNumber) {
		InternalTestHelper.setInternalUserNumber(userNumber);

		tourGuideService = new TourGuideServiceImpl(gpsUtil, rewardsService);
		/* NB : si fait remonter au niveau du before each, pb avec le set d'internal test helper
		parce que internal test helper est utilisé lors de l'instanciation de TourGuideService
		et a donc besoin de l'internalUserNumber sinon valeur par défaut est appliqué*/

		List<User> allUsers = tourGuideService.getAllUsers();

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		CompletableFuture<?>[] completableFutures = allUsers.stream()
				.map(tourGuideService::trackUserLocation)
				.toArray(CompletableFuture[]::new);

		CompletableFuture.allOf(completableFutures)
				.join();

		stopWatch.stop();
		tourGuideService.stopTracker();

		System.out.println("Track Location with "+ userNumber + " Users : Time Elapsed: " + TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds.");
		assertTrue(TimeUnit.MINUTES.toSeconds(15) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
	}


	@Test
	public void trackLocationWith100UsersTest() {
		int userNumber = 100;
		trackLocationWithXUsersModel(userNumber);
	}

	@Test
	public void trackLocationWith1000UsersTest() {
		int userNumber = 1000;
		trackLocationWithXUsersModel(userNumber);
	}

	@Test
	public void trackLocationWith5000UsersTest() {
		int userNumber = 5000;
		trackLocationWithXUsersModel(userNumber);
	}

	@Test
	public void trackLocationWith10000UsersTest() {
		int userNumber = 10000;
		trackLocationWithXUsersModel(userNumber);
	}

	@Test
	public void trackLocationWith50000UsersTest() {
		int userNumber = 50000;
		trackLocationWithXUsersModel(userNumber);
	}

	@Test
	public void trackLocationWith100000UsersTest() {
		int userNumber = 100000;
		trackLocationWithXUsersModel(userNumber);
	}

	// GET REWARDS PERFORMANCE TESTS //


	private void getRewardsWithXUsersModel(int userNumber) {
		InternalTestHelper.setInternalUserNumber(userNumber);

		tourGuideService = new TourGuideServiceImpl(gpsUtil, rewardsService);

		Attraction attraction = gpsUtil.getAttractions().get(0);

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		List<User> allUsers = tourGuideService.getAllUsers();

		allUsers.forEach(user -> {
			tourGuideService.addToVisitedLocationsOfUser(new VisitedLocation(user.getUserId(), attraction, new Date()), user);
		});

		CompletableFuture<?>[] completableFutures = allUsers.stream()
				.map(rewardsService::calculateRewards)
				.toArray(CompletableFuture[]::new);

		CompletableFuture.allOf(completableFutures)
				.join();

		allUsers.forEach(user -> assertTrue(user.getUserRewards().size() > 0));

		stopWatch.stop();
		tourGuideService.stopTracker();

		System.out.println("Get Rewards with " + userNumber + " Time Elapsed: " + TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds.");


		assertTrue(TimeUnit.MINUTES.toSeconds(20) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
	}


	@Test
	public void getRewardsWith100UsersTest() {
		int userNumber = 100;
		getRewardsWithXUsersModel(userNumber);
	}

	@Test
	public void getRewardsWith1000UsersTest() {
		int userNumber = 1000;
		getRewardsWithXUsersModel(userNumber);
	}

	@Test
	public void getRewardsWith5000UsersTest() {
		int userNumber = 5000;
		getRewardsWithXUsersModel(userNumber);
	}

	@Test
	public void getRewardsWith10000UsersTest() {
		int userNumber = 10000;
		getRewardsWithXUsersModel(userNumber);
	}

	@Test
	public void getRewardsWith50000UsersTest() {
		int userNumber = 50000;
		getRewardsWithXUsersModel(userNumber);
	}

	@Test
	public void getRewardsWith100000UsersTest() {
		int userNumber = 100000;
		getRewardsWithXUsersModel(userNumber);
	}
	
}
