package org.openclassrooms.tourguide.trackerapi.executor;

import org.openclassrooms.tourguide.models.model.user.User;

import java.util.concurrent.CompletableFuture;

public interface RewardExecutor {

    CompletableFuture<?> calculateRewards(User user);

    void addShutDownHook();
}
