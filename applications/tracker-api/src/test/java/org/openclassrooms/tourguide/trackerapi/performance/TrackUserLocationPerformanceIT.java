package org.openclassrooms.tourguide.trackerapi.performance;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.*;
import org.openclassrooms.tourguide.models.model.user.User;
import org.openclassrooms.tourguide.trackerapi.config.WebClientConfig;
import org.openclassrooms.tourguide.trackerapi.service.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TrackUserLocationPerformanceIT {

    //TODO

    private UserService userService;
    private LocationService locationService;
    private RewardService rewardService;
    private TrackerService trackerService;
    private static WebClient webClientUserApi;
    private static WebClient webClientGpsApi;
    private static WebClient webClientRewardApi;
    private static WebClientConfig webClientConfig;


    @BeforeAll
    public static void beforeAll() {
        Locale.setDefault(Locale.US);
        webClientConfig = new WebClientConfig();
        webClientUserApi = webClientConfig.getWebClientUserApi();
        webClientGpsApi = webClientConfig.getWebClientGpsApi();
        webClientRewardApi = webClientConfig.getWebClientRewardApi();
    }

    @BeforeEach
    public void beforeEach(){
        userService = new UserServiceImpl(webClientUserApi);
        locationService = new LocationServiceImpl(webClientGpsApi);
        rewardService = new RewardServiceImpl(webClientRewardApi, locationService);
        trackerService = new TrackerServiceImpl(locationService, rewardService);
    }

    @AfterEach
    public void afterEach() {
        trackerService.addShutDownHook();
        rewardService.addShutDownHook();
    }

    private void trackLocationWithXUsersModel(int userNumber) {
        //TODO : pour le nombre d'utilisateur, actuellement utilise la valeur par défaut, pas possible de set
		//InternalTestHelper.setInternalUserNumber(userNumber);

		//tourGuideService = new TourGuideServiceImpl(gpsUtil, rewardsService);
		/* NB : si fait remonter au niveau du before each, pb avec le set d'internal test helper
		parce que internal test helper est utilisé lors de l'instanciation de TourGuideService
		et a donc besoin de l'internalUserNumber sinon valeur par défaut est appliqué*/

        List<User> allUsers = userService.getAllUsers();

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		CompletableFuture<?>[] completableFutures = allUsers.stream()
				.map(trackerService::trackUserLocation)
				.toArray(CompletableFuture[]::new);

		CompletableFuture.allOf(completableFutures)
				.join();

		stopWatch.stop();

		System.out.println("\nTrack Location with " + allUsers.size() + " Users : Time Elapsed: " + TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds.\n");
		assertTrue(TimeUnit.MINUTES.toSeconds(15) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
	}

    @Order(1)
    @Test
    public void trackLocationWith100UsersTest() {
        int userNumber = 100;
        trackLocationWithXUsersModel(userNumber);
    }

   /*
    @Order(2)
    @Test
    public void trackLocationWith1000UsersTest() {
        int userNumber = 1000;
        trackLocationWithXUsersModel(userNumber);
    }

    @Order(3)
    @Test
    public void trackLocationWith5000UsersTest() {
        int userNumber = 5000;
        trackLocationWithXUsersModel(userNumber);
    }

    @Order(4)
    @Test
    public void trackLocationWith10000UsersTest() {
        int userNumber = 10000;
        trackLocationWithXUsersModel(userNumber);
    }

    @Order(5)
    @Test
    public void trackLocationWith50000UsersTest() {
        int userNumber = 50000;
        trackLocationWithXUsersModel(userNumber);
    }

    @Order(6)
    @Test
    public void trackLocationWith100000UsersTest() {
        int userNumber = 100000;
        trackLocationWithXUsersModel(userNumber);
    }
    */
}
