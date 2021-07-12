package org.openclassrooms.tourguide.trackerapi.service;

import org.openclassrooms.tourguide.models.model.user.User;

public interface RewardService {

    Void calculateRewards(User user);

    //TODO : for testing only, to clean
    void addShutDownHook();
}
