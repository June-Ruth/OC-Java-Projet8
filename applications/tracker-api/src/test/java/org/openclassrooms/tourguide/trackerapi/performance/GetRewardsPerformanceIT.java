package org.openclassrooms.tourguide.trackerapi.performance;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.*;
import org.openclassrooms.tourguide.models.model.location.Attraction;
import org.openclassrooms.tourguide.models.model.location.VisitedLocation;
import org.openclassrooms.tourguide.models.model.user.User;
import org.openclassrooms.tourguide.trackerapi.config.WebClientConfig;
import org.openclassrooms.tourguide.trackerapi.executor.RewardExecutor;
import org.openclassrooms.tourguide.trackerapi.executor.TrackerExecutor;
import org.openclassrooms.tourguide.trackerapi.service.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

//@Disabled
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GetRewardsPerformanceIT {

    //TODO

    private UserService userService;
    private LocationService locationService;
    private RewardService rewardService;
    private TrackerExecutor trackerExecutor;
    private RewardExecutor rewardExecutor;
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
        rewardService = new RewardServiceImpl(webClientRewardApi);
        trackerExecutor = new TrackerExecutor(locationService, userService, rewardExecutor);
        rewardExecutor = new RewardExecutor(locationService, userService, rewardService);
    }

    @AfterEach
    public void afterEach() {
        trackerExecutor.addShutDownHook();
        rewardExecutor.addShutDownHook();
    }

    private void getRewardsWithXUsersModel(int userNumber) {
        //TODO : pour le nombre d'utilisateur, actuellement utilise la valeur par défaut, pas possible de set
        //InternalTestHelper.setInternalUserNumber(userNumber);


        Attraction attraction = locationService.getAllAttractions().get(0);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        List<User> allUsers = userService.getAllUsers();

        allUsers.forEach(user -> userService.addToVisitedLocationsOfUser(new VisitedLocation(user.getUserId(), attraction, Date.from(Instant.now())), user));

        CompletableFuture<?>[] completableFutures = allUsers.stream()
                .map(rewardExecutor::calculateRewards)
                .toArray(CompletableFuture[]::new);

        try {
            CompletableFuture.allOf(completableFutures)
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        allUsers.forEach(user -> assertTrue(user.getUserRewards().size() > 0));

        stopWatch.stop();

        System.out.println("Get Rewards with " + userNumber + " Time Elapsed: " + TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds.");


        assertTrue(TimeUnit.MINUTES.toSeconds(20) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
    }

    @Order(1)
    @Test
    public void getRewardsWith100UsersTest() {
        int userNumber = 100;
        getRewardsWithXUsersModel(userNumber);
    }

    /*
    @Order(2)
    @Test
    public void getRewardsWith1000UsersTest() {
        int userNumber = 1000;
        getRewardsWithXUsersModel(userNumber);
    }

    @Order(3)
    @Test
    public void getRewardsWith5000UsersTest() {
        int userNumber = 5000;
        getRewardsWithXUsersModel(userNumber);
    }

    @Order(4)
    @Test
    public void getRewardsWith10000UsersTest() {
        int userNumber = 10000;
        getRewardsWithXUsersModel(userNumber);
    }

    @Order(5)
    @Test
    public void getRewardsWith50000UsersTest() {
        int userNumber = 50000;
        getRewardsWithXUsersModel(userNumber);
    }

    @Order(6)
    @Test
    public void getRewardsWith100000UsersTest() {
        int userNumber = 100000;
        getRewardsWithXUsersModel(userNumber);
    }
    */
}
