package tourGuide.service;

import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.openclassrooms.tourguide.models.model.User;
import org.openclassrooms.tourguide.models.model.UserReward;
import tripPricer.Provider;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public interface TourGuideService {

    List<Provider> getTripDeals(User user);

    CompletableFuture<?> trackUserLocation(User user);

    List<Attraction> getNearByAttractions(VisitedLocation visitedLocation);

    ExecutorService getExecutor();

    void stopTracker();

    VisitedLocation addToVisitedLocationsOfUser(VisitedLocation visitedLocation, User user);

}
