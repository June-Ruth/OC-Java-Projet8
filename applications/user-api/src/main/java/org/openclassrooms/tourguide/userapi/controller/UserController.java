package org.openclassrooms.tourguide.userapi.controller;

import org.openclassrooms.tourguide.models.model.location.VisitedLocation;
import org.openclassrooms.tourguide.models.model.user.User;
import org.openclassrooms.tourguide.models.model.user.UserPreferences;
import org.openclassrooms.tourguide.models.model.user.UserReward;
import org.openclassrooms.tourguide.userapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    public UserController(final UserService userService1) {
        userService = userService1;
    }

    /**
     * Access to user's information.
     * @param username of the user
     * @return user - if user doesn't exist, throw ElementNotFoundException
     */
    @GetMapping("/{username}")
    public User getUserProfile(@PathVariable final String username) {
        LOGGER.info("Getting user : " + username);
        User user = userService.getUser(username);
        return user;
    }

    /**
     * Access to user's preferences for trips.
     * @param username of the user
     * @return user preferences - if user doesn't exist, throw ElementNotFoundException
     */
    @GetMapping("/{username}/preferences")
    public UserPreferences getUserPreferences(@PathVariable final String username) {
        LOGGER.info("Getting user preferences for user : " + username);
        return userService.getUser(username).getUserPreferences();
    }

    /**
     * Update user's preferences for trips.
     * @param username of the user
     * @param updatedPreferences of the user
     * @return updated user preferences - if user doesn't exist, throw ElementNotFoundException
     */
    @PutMapping("/{username}/preferences")
    public UserPreferences updateUserPreferences(@PathVariable final String username,
                                                 @RequestBody final UserPreferences updatedPreferences) {
        LOGGER.info("Updating user preferences for user : " + username + " with preferences " + updatedPreferences);
        User user = userService.getUser(username);
        user.setUserPreferences(updatedPreferences);
        userService.updateUser(user);
        return updatedPreferences;
    }

    /**
     * Get all users' current locations.
     * @return a mapping of user id to their locations.
     */
    @GetMapping("/all-current-locations")
    public  List<VisitedLocation> getAllCurrentLocations() {
        LOGGER.info("Getting all users current locations.");
        return userService.getAllUserCurrentLocations();
    }

    /**
     * Get user's location for defined user.
     * @param username of the searched user
     * @return the last visited location of user - if user doesn't exist, throw ElementNotFoundException
     */
    @GetMapping("/{username}/current-location")
    public VisitedLocation getUserLocation(@PathVariable String username) {
        LOGGER.info("Getting user current location for user : " + username);
        return userService.getUserCurrentLocation(username);
    }

    /**
     * Access to user rewards for trips.
     * @param username of the user
     * @return list of user rewards - if user doesn't exist, throw ElementNotFoundException
     */
    @GetMapping("/{username}/rewards")
    public List<UserReward> getUserRewards(@PathVariable final String username) {
        LOGGER.info("Getting user rewards for user : " + username);
        return userService.getUser(username).getUserRewards();
    }
}
