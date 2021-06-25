package org.openclassrooms.tourguide.userprofile.controller;

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

    @GetMapping("/all-current-locations")
    public  List<VisitedLocation> getAllCurrentLocations() {
        return userService.getAllUserCurrentLocations();
    }

    @GetMapping("/{username}/current-location")
    public VisitedLocation getUserLocation(@PathVariable String username) {
        return userService.getUserCurrentLocation(username);
    }

    @GetMapping("/{username}/rewards")
    public List<UserReward> getUserRewards(@PathVariable final String username) {
        return userService.getUser(username).getUserRewards();
    }
}
