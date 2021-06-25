package org.openclassrooms.tourguide.webapp.service;

import org.openclassrooms.tourguide.models.model.location.VisitedLocation;
import org.openclassrooms.tourguide.models.model.user.User;
import org.openclassrooms.tourguide.models.model.user.UserPreferences;
import org.openclassrooms.tourguide.models.model.user.UserReward;

import java.util.List;

public interface TourGuideService {

    /**
     * Get all user's current location.
     * @return list of each last visited location
     */
    List<VisitedLocation> getAllUserCurrentLocations();

    /**
     * Get user's current location for defined user.
     * @param username of the searched user
     * @return the last visited location of user - if user doesn't exist, throw ElementNotFoundException
     */
    VisitedLocation getUserCurrentLocation(String username);

    /**
     * Get user information
     * @param username of the searched user
     * @return user - if user doesn't exist, throw ElementNotFoundException
     */
    User getUser(String username);

    /**
     * Get user preferences
     * @param username of the searched user
     * @return user preferences - if user doesn't exist, throw ElementNotFoundException
     */
    UserPreferences getUserPreferences(String username);

    /**
     * Update user preferences.
     * @param username of the searched user
     * @param updatedPreferences of the user
     * @return updated user preferences - if user doesn't exist, throw ElementNotFoundException
     */
    UserPreferences updateUserPreferences(String username, UserPreferences updatedPreferences);

    /**
     * Get user rewards
     * @param username of the searched user
     * @return list of user rewards - if user doesn't exist, throw ElementNotFoundException
     */
    List<UserReward> getUserRewards(String username);
}
