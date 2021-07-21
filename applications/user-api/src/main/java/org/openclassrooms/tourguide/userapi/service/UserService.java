package org.openclassrooms.tourguide.userapi.service;


import org.openclassrooms.tourguide.models.model.location.VisitedLocation;
import org.openclassrooms.tourguide.models.model.user.User;

import java.util.List;

public interface UserService {

    /**
     * Get all registered users.
     * @return list of all users
     */
    List<User> getAllUsers();

    /**
     * Get user by its username.
     * @param username of searched user
     * @return found user - if user doesn't exist throw ElementNotFoundException
     */
    User getUser(String username);

    /**
     * Add a new user.
     * @param user to add
     * @return added user - if user already exists throw ElementAlreadyExistingException
     */
    User addUser(User user);

    /**
     * Update a existing user.
     * @param updatedUser completed
     * @return updated user - if user doesn't exist throw ElementNotFoundException
     */
    User updateUser(User updatedUser);

    /**
     * Get user current location.
     * @param username of searched user
     * @return last visited location of user - if user doesn't exist throw ElementNotFoundException
     */
    VisitedLocation getUserCurrentLocation(String username);

    /**
     * Get all current location for all user.
     * @return list of visited location
     */
    List<VisitedLocation> getAllUserCurrentLocations();
}
