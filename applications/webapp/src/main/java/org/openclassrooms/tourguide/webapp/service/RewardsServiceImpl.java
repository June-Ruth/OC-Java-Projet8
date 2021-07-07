package org.openclassrooms.tourguide.webapp.service;

import org.openclassrooms.tourguide.models.model.location.Attraction;
import org.openclassrooms.tourguide.models.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class RewardsServiceImpl implements RewardsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RewardsServiceImpl.class);

    private final WebClient webClientRewardApi;

    public RewardsServiceImpl(@Qualifier("getWebClientRewardApi")final WebClient webClientRewardApi1) {
        webClientRewardApi = webClientRewardApi1;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getAttractionRewardPoints(final Attraction attraction, final User user) {
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

   /* public CompletableFuture<?> calculateRewards(User user) {
        List<UserReward> userRewards = new ArrayList<>(user.getUserRewards());

        CompletableFuture<?> completableFuture = CompletableFuture.runAsync(() ->
                        new ArrayList<>(user.getVisitedLocations())
                                .forEach(visitedLocation ->
                                        getAttractionsNearVisitedLocation(visitedLocation)
                                                .stream()
                                                .filter( attraction ->
                                                        isAttractionNotAlreadyInUserRewards(attraction, userRewards)
                                                )
                                                .forEach(attraction ->
                                                        addUserRewardToUser(new UserReward(visitedLocation, attraction, getRewardPoints(attraction, user)), user)
                                                )
                                )
                ,executor);
        return completableFuture;
    }*/
}
