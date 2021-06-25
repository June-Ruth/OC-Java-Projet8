package org.openclassrooms.tourguide.webapp.controller;

import org.openclassrooms.tourguide.models.model.User;
import org.openclassrooms.tourguide.models.model.UserPreferences;
import org.openclassrooms.tourguide.models.model.UserReward;
import org.openclassrooms.tourguide.webapp.dto.UserContactsDto;
import org.openclassrooms.tourguide.webapp.service.TourGuideService;
import org.openclassrooms.tourguide.webapp.util.DtoConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserWebController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserWebController.class);

    private TourGuideService tourGuideService;

    public UserWebController(final TourGuideService tourGuideService1) {
        tourGuideService = tourGuideService1;
    }

    /*
     * Get the closest five attractions to the user, no matter how far away they are.
     * @param username of user - may throw 404 exception if user doesn't exist.
     * @return nearAttractionDto
     * @see NearAttractionDto
     */
   /* @GetMapping("/users/{username}/closest-attractions")
    public List<NearAttractionDto> getUserFiveClosestAttractions(@PathVariable String username) {

        // NB manque ces deux étapes dans le process
        // Return The distance in miles between the user's location and each of the attractions.
        // Note: Attraction reward points can be gathered from RewardsCentral

        List<Attraction> fiveNearestAttractions = userService.getUserFiveClosestAttractions(username);
        List<NearAttractionDto> fiveNearestAttractionsDto = new ArrayList<>();

        for(Attraction attraction : fiveNearestAttractions) {
            int rewardPoints = userService.getAttractionRewardPoints(attraction);
            NearAttractionDto nearAttractionDTO = DtoConverter.convertAttractionToNearAttractionDto(attraction, userService.getUserCurrentLocation(username).location, rewardPoints);
            fiveNearestAttractionsDto.add(nearAttractionDTO);
        }
        return fiveNearestAttractionsDto;
    }*/

    /**
     * Home Page for current user on the app.
     * @return home message
     */
    @GetMapping("/home")
    public String homePage() {
        LOGGER.info("Getting home page.");
        return "Greetings from TourGuide Enterprise ! Welcome User.";
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
        User user = tourGuideService.getUser(username);
        return  DtoConverter.convertUserToUserContactsDto(user);
    }

    /**
     * As authenticated user, access to its preferences for trips.
     * Authentication is not implemented yet.
     * @param username of the authenticated user
     * @return user preferences - if user doesn't exist, throw ElementNotFoundException
     */
    @GetMapping("/preferences")
    public UserPreferences getUserPreferences(@RequestParam(name = "username") final String username) {
        LOGGER.info("Getting user preferences for user : " + username);
        return tourGuideService.getUserPreferences(username);
    }

    /**
     * As authenticated user, update its preferences for trips.
     * Authentication is not implemented yet.
     * @param username of the authenticated user
     * @param updatedPreferences of the authenticated user
     * @return updated user preferences - if user doesn't exist, throw ElementNotFoundException
     */
    @PutMapping("/preferences")
    public UserPreferences updateUserPreferences(@RequestParam(name = "username") final String username,
                                                 @RequestBody final UserPreferences updatedPreferences) {
        LOGGER.info("Updating user preferences for user : " + username + " with preferences : " + updatedPreferences);
        return tourGuideService.updateUserPreferences(username, updatedPreferences);
    }

    /**
     * As authenticated user, access to its rewards for trips.
     * Authentication is not implemented yet.
     * @param username of the authenticated user
     * @return list of user rewards - if user doesn't exist, throw ElementNotFoundException
     */
    @GetMapping("/rewards")
    public List<UserReward> getUserRewards(@RequestParam(name = "username") final String username) {
        LOGGER.info("Getting user rewards for user : " + username);
        return tourGuideService.getUserRewards(username);
    }

    /* TODO : Feature List - As a user, I want ... :
        - To get attraction information
            - LocalizationMS : /attractions/{attractionName} => READY
        - To get attraction proposals near my location
            - UserMS : GET : /users/{username}/current-location => READY
            - LocalizationMS : GET : /attractions/closest-five
            - Get rewards for each attractions
        - To get trip deals
     */

}
