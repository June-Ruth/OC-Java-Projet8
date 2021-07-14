package org.openclassrooms.tourguide.trackerapi.executor;

import org.openclassrooms.tourguide.models.model.user.User;

import java.util.concurrent.CompletableFuture;

public interface TrackerExecutor {

    CompletableFuture<?> trackUserLocation(User user);

    void addShutDownHook();
}
