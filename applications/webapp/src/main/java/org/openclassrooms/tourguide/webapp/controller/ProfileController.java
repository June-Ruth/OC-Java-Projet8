package org.openclassrooms.tourguide.webapp.controller;

import org.openclassrooms.tourguide.models.model.user.User;
import org.openclassrooms.tourguide.models.model.user.UserPreferences;
import org.openclassrooms.tourguide.models.model.user.UserReward;
import org.openclassrooms.tourguide.webapp.dto.UserContactsDto;
import org.openclassrooms.tourguide.webapp.service.UserService;
import org.openclassrooms.tourguide.webapp.util.DtoConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileController.class);

    private final UserService userService;

    public ProfileController(final UserService userService1) {
        userService = userService1;
    }

    /**
     * As authenticated user, access to its profile information.
     * Authentication is not implemented yet.
     * @param username of the authenticated user
     * @return user profile information - if user doesn't exist, throw ElementNotFoundException
     * @see UserContactsDto
     */
    @GetMapping("/profile")
    public UserContactsDto getUserProfile(@RequestParam(name = "username") final String username) {
        LOGGER.info("Getting user profile for user : " + username);
        User user = userService.getUser(username);
        return  DtoConverter.convertUserToUserContactsDto(user);
    }

    /**
     * As authenticated user, access to its preferences for trips.
     * Authentication is not implemented yet.
     * @param username of the authenticated user
     * @return user preferences - if user doesn't exist, throw ElementNotFoundException
     */
    @GetMapping("/profile/preferences")
    public UserPreferences getUserPreferences(@RequestParam(name = "username") final String username) {
        LOGGER.info("Getting user preferences for user : " + username);
        return userService.getUserPreferences(username);
    }

    /**
     * As authenticated user, update its preferences for trips.
     * Authentication is not implemented yet.
     * @param username of the authenticated user
     * @param updatedPreferences of the authenticated user
     * @return updated user preferences - if user doesn't exist, throw ElementNotFoundException
     */
    @PutMapping("/profile/preferences")
    public UserPreferences updateUserPreferences(@RequestParam(name = "username") final String username,
                                                 @RequestBody final UserPreferences updatedPreferences) {
        LOGGER.info("Updating user preferences for user : " + username + " with preferences : " + updatedPreferences);
        return userService.updateUserPreferences(username, updatedPreferences);
    }

    /**
     * As authenticated user, access to its rewards for trips.
     * Authentication is not implemented yet.
     * @param username of the authenticated user
     * @return list of user rewards - if user doesn't exist, throw ElementNotFoundException
     */
    @GetMapping("/profile/rewards")
    public List<UserReward> getUserRewards(@RequestParam(name = "username") final String username) {
        LOGGER.info("Getting user rewards for user : " + username);
        return userService.getUserRewards(username);
    }
}
