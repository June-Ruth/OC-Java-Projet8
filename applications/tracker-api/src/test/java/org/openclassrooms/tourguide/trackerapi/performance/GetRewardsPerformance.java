package org.openclassrooms.tourguide.trackerapi.performance;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.*;
import org.openclassrooms.tourguide.models.model.location.Attraction;
import org.openclassrooms.tourguide.models.model.location.VisitedLocation;
import org.openclassrooms.tourguide.models.model.user.User;
import org.openclassrooms.tourguide.trackerapi.config.WebClientConfig;
import org.openclassrooms.tourguide.trackerapi.executor.RewardExecutor;
import org.openclassrooms.tourguide.trackerapi.executor.RewardExecutorImpl;
import org.openclassrooms.tourguide.trackerapi.service.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
public class GetRewardsPerformance {

    private UserService userService;
    private LocationService locationService;
    private RewardService rewardService;
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
    public void beforeEach(){
        allUsers = new ArrayList<>();
        stopWatch = new StopWatch();
        userService = new UserServiceImpl(webClientUserApi);
        locationService = new LocationServiceImpl(webClientGpsApi);
        rewardService = new RewardServiceImpl(webClientRewardApi);
        rewardExecutor = new RewardExecutorImpl(locationService, userService, rewardService);
    }

    @AfterEach
    public void afterEach() {
        rewardExecutor.addShutDownHook();
        System.out.println("\nGet Rewards with " + allUsers.size() + " users. Time Elapsed: " + TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds.\n");
        stopWatch.reset();
    }

    @Test
    void getUserRewardsPerformanceIT() {
        /*
        To set user number, use user-api InternalTestHelper default value before running it.
         */

        Attraction attraction = locationService.getAllAttractions().get(0);

        allUsers = userService.getAllUsers();

        allUsers.forEach(user -> userService.addToVisitedLocationsOfUser(new VisitedLocation(user.getUserId(), attraction, Date.from(Instant.now())), user));

        stopWatch.start();

        CompletableFuture<?>[] completableFutures = allUsers.stream()
                .map(rewardExecutor::calculateRewards)
                .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(completableFutures)
                .join();

        stopWatch.stop();

        //allUsers.forEach(user -> assertTrue(user.getUserRewards().size() > 0));

        assertTrue(TimeUnit.MINUTES.toSeconds(20) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
    }
}
