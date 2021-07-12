package org.openclassrooms.tourguide.trackerapi.service;

import org.openclassrooms.tourguide.models.model.user.User;

import java.util.concurrent.CompletableFuture;

public interface LocationService {

    CompletableFuture<?> trackUserLocation(User user);

}
