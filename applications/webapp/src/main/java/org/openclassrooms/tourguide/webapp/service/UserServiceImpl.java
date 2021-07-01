package org.openclassrooms.tourguide.webapp.service;

import org.openclassrooms.tourguide.models.model.location.VisitedLocation;
import org.openclassrooms.tourguide.models.model.user.User;
import org.openclassrooms.tourguide.models.model.user.UserPreferences;
import org.openclassrooms.tourguide.models.model.user.UserReward;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    public UserServiceImpl() {

    }

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
        // NB : si UserCurrentLocation peut revenir null, si null alors besoin de trackUSerLocation avec Tracker => vérifier éventuellement si situation probable et nécessaire mais initialement prévue
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
}
