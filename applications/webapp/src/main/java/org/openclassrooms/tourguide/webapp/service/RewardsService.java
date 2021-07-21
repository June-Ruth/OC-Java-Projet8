package org.openclassrooms.tourguide.webapp.service;

import org.openclassrooms.tourguide.models.model.location.Attraction;
import org.openclassrooms.tourguide.models.model.user.User;

public interface RewardsService {

    /**
     * Calculate the rewards for a specified attraction.
     * @param attraction specified
     * @param user
     * @return rewards as int
     */
    int getAttractionRewardPoints(Attraction attraction, User user);
}
