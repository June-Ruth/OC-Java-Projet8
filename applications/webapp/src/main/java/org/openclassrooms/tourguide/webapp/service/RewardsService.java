package org.openclassrooms.tourguide.webapp.service;

import org.openclassrooms.tourguide.models.model.location.Attraction;

public interface RewardsService {

    /**
     * Calculate the rewards for a specified attraction.
     * @param attraction specified
     * @return rewards as int
     */
    int calculateRewards(Attraction attraction);
}
