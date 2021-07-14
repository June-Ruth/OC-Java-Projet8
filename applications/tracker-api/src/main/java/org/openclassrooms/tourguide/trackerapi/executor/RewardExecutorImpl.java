package org.openclassrooms.tourguide.trackerapi.executor;

import org.openclassrooms.tourguide.models.model.location.Attraction;
import org.openclassrooms.tourguide.models.model.user.User;
import org.openclassrooms.tourguide.models.model.user.UserReward;
import org.openclassrooms.tourguide.trackerapi.service.LocationService;
import org.openclassrooms.tourguide.trackerapi.service.RewardService;
import org.openclassrooms.tourguide.trackerapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class RewardExecutorImpl implements  RewardExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(RewardExecutorImpl.class);

    private final LocationService locationService;
    private final UserService userService;
    private final RewardService rewardService;

    private final ExecutorService executor;

    public RewardExecutorImpl(final LocationService locationService1,
                              final UserService userService1,
                              final RewardService rewardService1) {
        locationService = locationService1;
        userService = userService1;
        rewardService = rewardService1;
        executor = Executors.newFixedThreadPool(800);
    }

    @Override
    public CompletableFuture<?> calculateRewards(User user) {
        //TODO ; IT
        LOGGER.info("Calculating rewards for user " + user);
        List<UserReward> userRewards = new ArrayList<>(user.getUserRewards());

        return CompletableFuture.runAsync(() ->
                        new ArrayList<>(user.getVisitedLocations())
                                .forEach(visitedLocation ->
                                        locationService.getAttractionsNearVisitedLocation(visitedLocation)
                                                .stream()
                                                .filter( attraction ->
                                                        isAttractionNotAlreadyInUserRewards(attraction, userRewards)
                                                )
                                                .forEach(attraction ->
                                                        userService.addUserRewardToUser(new UserReward(visitedLocation, attraction, rewardService.getAttractionRewardPoints(attraction, user)), user)
                                                )
                                )
                ,executor);
    }

    @Override
    public void addShutDownHook() {
        LOGGER.info("Reward Executor is shutting down");
        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            executor.shutdownNow();
        } finally {
            Thread.currentThread().interrupt();
        }
    }

    private boolean isAttractionNotAlreadyInUserRewards(Attraction attraction, List<UserReward> userRewards) {
        LOGGER.info("Checking if attraction " + attraction + " is already in user rewards " + userRewards);
        return userRewards
                .stream()
                .noneMatch(userReward ->
                        userReward.getAttraction().getAttractionName().equals(attraction.getAttractionName()));
    }
}
