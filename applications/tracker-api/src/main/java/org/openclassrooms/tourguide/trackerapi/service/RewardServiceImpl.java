package org.openclassrooms.tourguide.trackerapi.service;

import org.openclassrooms.tourguide.models.model.location.Attraction;
import org.openclassrooms.tourguide.models.model.location.Location;
import org.openclassrooms.tourguide.models.model.location.VisitedLocation;
import org.openclassrooms.tourguide.models.model.user.User;
import org.openclassrooms.tourguide.models.model.user.UserReward;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class RewardServiceImpl implements RewardService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RewardServiceImpl.class);

    private final WebClient webClientRewardApi;
    private final LocationService locationService;

    ExecutorService executor = Executors.newFixedThreadPool(800);

    public RewardServiceImpl(@Qualifier("getWebClientRewardApi")final WebClient webClientRewardApi1,
                             final LocationService locationService1) {
        webClientRewardApi = webClientRewardApi1;
        locationService = locationService1;
    }

    @Override
    public Void calculateRewards(User user) {
        LOGGER.info("Calculating rewards for user " + user);
        List<UserReward> userRewards = new ArrayList<>(user.getUserRewards());

        return CompletableFuture.runAsync(() ->
                        new ArrayList<>(user.getVisitedLocations())
                                .forEach(visitedLocation ->
                                        getAttractionsNearVisitedLocation(visitedLocation)
                                                .stream()
                                                .filter( attraction ->
                                                        isAttractionNotAlreadyInUserRewards(attraction, userRewards)
                                                )
                                                .forEach(attraction ->
                                                        addUserRewardToUser(new UserReward(visitedLocation, attraction, getAttractionRewardPoints(attraction, user)), user)
                                                )
                                )
                ,executor).join();
    }

    @Override
    //TODO : for testing only, to clean
    public void addShutDownHook() {
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

    private int getAttractionRewardPoints(final Attraction attraction, final User user) {
        LOGGER.info("Get attraction reward points for attraction "
                + attraction.getAttractionName() + " and user " + user.getUsername());
        return webClientRewardApi
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/rewards")
                        .queryParam("attractionId", attraction.getAttractionId())
                        .queryParam("userId", user.getUserId())
                        .build())
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
    }

    private void addUserRewardToUser(UserReward userReward, User user) {
        LOGGER.info("Adding user reward " + userReward + " to user " + user);
        List<UserReward> userRewards = user.getUserRewards();
        if(userRewards
                .stream()
                .noneMatch(reward ->
                        reward.getAttraction().getAttractionName().equals(userReward.getAttraction().getAttractionName()))) {
            userRewards.add(userReward);
        }
    }

    private List<Attraction> getAttractionsNearVisitedLocation(VisitedLocation visitedLocation) {
        LOGGER.info("Getting all attractions at proximity.");
        return locationService.getAllAttractions()
                .stream()
                .filter(attraction ->
                        isNear(visitedLocation, attraction))
                .collect(Collectors.toList());
    }

    private static final int PROXIMITY_BUFFER = 10;

    private boolean isNear(final VisitedLocation visitedLocation, final Attraction attraction) {
        LOGGER.info("Checking if visited location : " + visitedLocation + " is near of attraction " + attraction);
        return !(getDistance(attraction, visitedLocation.getLocation()) > PROXIMITY_BUFFER);
    }

    private static final double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;

    private double getDistance(Location location1, Location location2) {
        LOGGER.info("Getting distance between " + location1 + " and " + location2);
        double latitude1 = Math.toRadians(location1.getLatitude());
        double longitude1 = Math.toRadians(location1.getLongitude());
        double latitude2 = Math.toRadians(location2.getLatitude());
        double longitude2 = Math.toRadians(location2.getLongitude());
        double angle = Math.acos(Math.sin(latitude1) * Math.sin(latitude2)
                + Math.cos(latitude1) * Math.cos(latitude2) * Math.cos(longitude1 - longitude2));
        double nauticalMiles = 60 * Math.toDegrees(angle);
        return STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
    }

    private boolean isAttractionNotAlreadyInUserRewards(Attraction attraction, List<UserReward> userRewards) {
        LOGGER.info("Checking if attraction " + attraction + " is already in user rewards " + userRewards);
        return userRewards
                .stream()
                .noneMatch(userReward ->
                        userReward.getAttraction().getAttractionName().equals(attraction.getAttractionName()));
    }

}
