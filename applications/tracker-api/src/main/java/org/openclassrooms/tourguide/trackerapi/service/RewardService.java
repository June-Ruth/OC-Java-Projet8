package org.openclassrooms.tourguide.trackerapi.service;

import org.openclassrooms.tourguide.models.model.location.Attraction;
import org.openclassrooms.tourguide.models.model.user.User;

public interface RewardService {

    int getAttractionRewardPoints(final Attraction attraction, final User user);
}
