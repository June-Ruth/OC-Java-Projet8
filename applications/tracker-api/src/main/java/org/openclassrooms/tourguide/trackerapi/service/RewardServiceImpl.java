package org.openclassrooms.tourguide.trackerapi.service;

import org.openclassrooms.tourguide.models.model.location.Attraction;
import org.openclassrooms.tourguide.models.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class RewardServiceImpl implements RewardService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RewardServiceImpl.class);

    private final WebClient webClientRewardApi;

    public RewardServiceImpl(@Qualifier("getWebClientRewardApi")final WebClient webClientRewardApi1) {
        webClientRewardApi = webClientRewardApi1;
    }

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
}
