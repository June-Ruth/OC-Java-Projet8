package org.openclassrooms.tourguide.webapp.service;

import org.openclassrooms.tourguide.models.model.location.Attraction;
import org.openclassrooms.tourguide.models.model.user.User;
import org.openclassrooms.tourguide.models.model.user.UserReward;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class RewardsServiceImpl implements RewardsService {

    /**
     * @inheritDoc
     */
    @Override
    public int getAttractionRewardPoints(Attraction attraction) {
        //TODO : WebClient Reward API -> getRewardPoints -> GET "/rewards?attractionId= &userId= "
        return 0;
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
