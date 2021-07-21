package org.openclassrooms.tourguide.rewardapi.controller;

import org.openclassrooms.tourguide.rewardapi.service.RewardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class RewardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RewardController.class);

    private final RewardService rewardService;

    public RewardController(final RewardService rewardService1) {
        rewardService = rewardService1;
    }

    /**
     * Calculate rewards points depending on attraction and user.
     * @param attractionId concerned
     * @param userId concerned
     * @return rewards point as int
     */
    @GetMapping("/rewards")
    public int getRewardPoints(@RequestParam final UUID attractionId, @RequestParam final UUID userId) {
        LOGGER.info("Getting rewards points for attraction " + attractionId + " and user " + userId);
        return rewardService.getRewardPoints(attractionId, userId);
    }

}
