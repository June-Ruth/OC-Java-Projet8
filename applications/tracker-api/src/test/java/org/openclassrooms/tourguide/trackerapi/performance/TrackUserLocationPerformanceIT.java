package org.openclassrooms.tourguide.trackerapi.performance;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.*;
import org.openclassrooms.tourguide.models.model.user.User;
import org.openclassrooms.tourguide.trackerapi.config.WebClientConfig;
import org.openclassrooms.tourguide.trackerapi.executor.RewardExecutor;
import org.openclassrooms.tourguide.trackerapi.executor.RewardExecutorImpl;
import org.openclassrooms.tourguide.trackerapi.executor.TrackerExecutor;
import org.openclassrooms.tourguide.trackerapi.executor.TrackerExecutorImpl;
import org.openclassrooms.tourguide.trackerapi.service.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
public class TrackUserLocationPerformanceIT {

    private UserService userService;
    private LocationService locationService;
    private RewardService rewardService;
    private TrackerExecutor trackerExecutor;
    private RewardExecutor rewardExecutor;
    private static WebClient webClientUserApi;
    private static WebClient webClientGpsApi;
    private static WebClient webClientRewardApi;
    private static WebClientConfig webClientConfig;

    private List<User> allUsers;
    private StopWatch stopWatch;


    @BeforeAll
    public static void beforeAll() {
        Locale.setDefault(Locale.US);
        webClientConfig = new WebClientConfig();
        webClientUserApi = webClientConfig.getWebClientUserApi();
        webClientGpsApi = webClientConfig.getWebClientGpsApi();
        webClientRewardApi = webClientConfig.getWebClientRewardApi();
    }

    @BeforeEach
    public void beforeEach() {
        allUsers = new ArrayList<>();
        stopWatch = new StopWatch();
        userService = new UserServiceImpl(webClientUserApi);
        locationService = new LocationServiceImpl(webClientGpsApi);
        rewardService = new RewardServiceImpl(webClientRewardApi);
        rewardExecutor = new RewardExecutorImpl(locationService, userService, rewardService);
        trackerExecutor = new TrackerExecutorImpl(locationService, userService, rewardExecutor);
    }

    @AfterEach
    public void afterEach() {
        trackerExecutor.addShutDownHook();
        System.out.println("\nTrack Location with " + allUsers.size() + " users. Time Elapsed: " + TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds.\n");
        stopWatch.reset();
    }

    @Test
    void trackUserLocationPerformanceIT() {
        /*
        To set user number, use user-api InternalTestHelper default value before running it.
         */

        allUsers = userService.getAllUsers();

		stopWatch.start();

		CompletableFuture<?>[] completableFutures = allUsers.stream()
				.map(trackerExecutor::trackUserLocation)
				.toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(completableFutures)
                .join();

        stopWatch.stop();

		assertTrue(TimeUnit.MINUTES.toSeconds(15) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
	}
}
