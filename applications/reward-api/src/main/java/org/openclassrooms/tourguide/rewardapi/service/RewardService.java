package org.openclassrooms.tourguide.rewardapi.service;

import java.util.UUID;

public interface RewardService {

    /**
     * Calculate rewards points depending on attraction and user.
     * @param attractionId concerned
     * @param userId concerned
     * @return rewards point as int
     */
    int getRewardPoints(UUID attractionId, UUID userId);
}
