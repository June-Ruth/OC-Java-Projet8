package org.openclassrooms.tourguide.webapp.service;

import gpsUtil.location.VisitedLocation;
import org.openclassrooms.tourguide.models.model.User;
import org.openclassrooms.tourguide.models.model.UserPreferences;
import org.openclassrooms.tourguide.models.model.UserReward;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourGuideServiceImpl implements TourGuideService {

    /**
     * @inheritDoc
     */
    @Override
    public List<VisitedLocation> getAllUserCurrentLocations() {
        //TODO : WebClient User API -> UserController -> getAllCurrentLocations -> GET : "/users/all-current-locations"
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public VisitedLocation getUserCurrentLocation(String username) {
        //TODO : WebClient User API -> UserController -> getUserLocation -> GET : "/users/{username}/current-location"
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public User getUser(String username) {
        //TODO : WebClient User API -> UserController -> getUser -> GET : "/users/{username}
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public UserPreferences getUserPreferences(String username) {
        //TODO : WebClient User API -> UserController -> getUserPreferences -> GET : "/users/{username}/preferences
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public UserPreferences updateUserPreferences(String username, UserPreferences updatedPreferences) {
        //TODO : WebClient User API -> UserController -> getUserPreferences -> PUT : "/users/{username}/preferences
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<UserReward> getUserRewards(String username) {
        //TODO : WebClient User API -> UserController -> getUserRewards -> GET : "/users/{username}/rewards
        return null;
    }

    /*@Override
    public List<Attraction> getUserFiveClosestAttractions(String username) {
        //TODO : Test
        double userCurrentLatitude = getUserCurrentLocation(username).location.latitude;
        double userCurrentLongitude = getUserCurrentLocation(username).location.longitude;
        List<Attraction> fiveNearestAttraction = new ArrayList<>();

        List<Attraction> fiveClosestAttractions = webClientAttraction.get()
                .uri("/attractions/closest-five?latitude=" + userCurrentLatitude + "&longitude=" + userCurrentLongitude)
                .retrieve().bodyToFlux(Attraction.class).collectList().block();
        return fiveClosestAttractions;
    }

    @Override
    public int getAttractionRewardPoints(Attraction attraction) {
        //TODO ; aller le chercher dans le calculate Rewards
        return 0;
    }*/


}
