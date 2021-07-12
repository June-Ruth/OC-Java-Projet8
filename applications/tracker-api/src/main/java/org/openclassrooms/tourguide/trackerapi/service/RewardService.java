package org.openclassrooms.tourguide.trackerapi.service;

import org.openclassrooms.tourguide.models.model.user.User;

import java.util.concurrent.CompletableFuture;

public interface RewardService {

    CompletableFuture<?> calculateRewards(User user);
}
