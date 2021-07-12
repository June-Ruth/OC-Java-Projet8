package org.openclassrooms.tourguide.trackerapi.service;

import org.openclassrooms.tourguide.models.model.location.VisitedLocation;
import org.openclassrooms.tourguide.models.model.user.User;

import java.util.concurrent.CompletableFuture;

public interface TrackerService {

    CompletableFuture<?> trackUserLocation(final User user);

    VisitedLocation addToVisitedLocationsOfUser(VisitedLocation visitedLocation, User user);

    public void addShutDownHook();
}
