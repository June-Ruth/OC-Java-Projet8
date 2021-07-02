package org.openclassrooms.tourguide.rewardapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import rewardCentral.RewardCentral;

import java.util.UUID;

@Service
public class RewardServiceImpl implements RewardService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RewardServiceImpl.class);

    private final RewardCentral rewardCentral;

    public RewardServiceImpl(final RewardCentral rewardCentral1) {
        rewardCentral = rewardCentral1;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getRewardPoints(UUID attractionId, UUID userId) {
        LOGGER.info("Getting rewards points for attraction " + attractionId + " and user " + userId);
        return rewardCentral.getAttractionRewardPoints(attractionId, userId);
    }
}
