package org.openclassrooms.tourguide.userprofile.controller;

import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import org.openclassrooms.tourguide.models.model.User;
import org.openclassrooms.tourguide.models.model.UserPreferences;
import org.openclassrooms.tourguide.models.model.UserReward;
import org.openclassrooms.tourguide.userprofile.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/users")
@RestController
public class UserController {

    private UserService userService;

    public UserController(final UserService userService1) {
        userService = userService1;
    }

    @GetMapping("/{username}")
    public User getUserProfile(@PathVariable final String username) {
        User user = userService.getUser(username);
        return user;
    }

    @PutMapping("/{username}")
    public User updateUser(@PathVariable final String username,
                           @RequestBody final User updatedUser) {
        User user = userService.getUser(username);
        user.setPhoneNumber(updatedUser.getPhoneNumber());
        user.setEmailAddress(updatedUser.getEmailAddress());
        userService.updateUser(user);
        return updatedUser;
    }

    @GetMapping("/{username}/preferences")
    public UserPreferences getUserPreferences(@PathVariable final String username) {
        return userService.getUser(username).getUserPreferences();
    }

    @PutMapping("/{username}/preferences")
    public UserPreferences updateUserPreferences(@PathVariable final String username,
                                                 @RequestBody final UserPreferences updatedPreferences) {
        User user = userService.getUser(username);
        user.setUserPreferences(updatedPreferences);
        userService.updateUser(user);
        return updatedPreferences;
    }

    // Note: does not use gpsUtil to query for their current location,
    //        but rather gathers the user's current location from their stored location history.
    // Return object should be the just a JSON mapping of userId to Locations similar to:
    // { "019b04a9-067a-4c76-8817-ee75088c3822": {"longitude":-48.188821,"latitude":74.84371} ... }
    @GetMapping("/all-current-locations")
    public Map<UUID, Location> getAllCurrentLocations() {
        List<VisitedLocation> allUserCurrentLocations = userService.getAllUserCurrentLocations();

        Map<UUID, Location> result = new HashMap<>();

        for(VisitedLocation visitedLocation : allUserCurrentLocations) {
            result.put(visitedLocation.userId, visitedLocation.location);
        }
        return result;
    }

    @GetMapping("/{username}/current-location")
    public Location getUserLocation(@PathVariable String username) {
        VisitedLocation lastVisitedLocation = userService.getUserCurrentLocation(username);
        return lastVisitedLocation.location;
    }

    @GetMapping("/{username}/rewards")
    public List<UserReward> getUserRewards(@PathVariable final String username) {
        return userService.getUser(username).getUserRewards();
    }
}
