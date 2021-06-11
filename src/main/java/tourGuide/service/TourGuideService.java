package tourGuide.service;

import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import tourGuide.model.User;
import tourGuide.model.UserReward;
import tripPricer.Provider;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public interface TourGuideService {

    List<UserReward> getUserRewards(User user);

    VisitedLocation getUserLocation(User user);

    User getUser(String userName);

    List<User> getAllUsers();

    void addUser(User user);

    List<Provider> getTripDeals(User user);

    CompletableFuture<?> trackUserLocation(User user);

    List<Attraction> getNearByAttractions(VisitedLocation visitedLocation);

    ExecutorService getExecutor();

    void stopTracker();

    void clearVisitedLocationsOfUser(User user);

    VisitedLocation addToVisitedLocationsOfUser(VisitedLocation visitedLocation, User user);

}
