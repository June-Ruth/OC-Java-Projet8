package org.openclassrooms.tourguide.webapp.controller;

import org.openclassrooms.tourguide.models.model.location.Location;
import org.openclassrooms.tourguide.models.model.location.VisitedLocation;
import org.openclassrooms.tourguide.webapp.service.TourGuideService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/admin")
@RestController
public class AdminWebController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminWebController.class);

    private TourGuideService tourGuideService;

    public AdminWebController(final TourGuideService tourGuideService1) {
        tourGuideService = tourGuideService1;
    }

    @GetMapping("/home")
    public String homePage() {
        return "Greetings from TourGuide Enterprise ! Welcome Admin.";
    }

    // Note: does not use gpsUtil to query for their current location,
    //        but rather gathers the user's current location from their stored location history.
    // Return object should be the just a JSON mapping of userId to Locations similar to:
    // { "019b04a9-067a-4c76-8817-ee75088c3822": {"longitude":-48.188821,"latitude":74.84371} ... }

    /**
     * As administrator, see all users' current locations.
     * @return a mapping of user id to their locations.
     */
    @GetMapping("/users/all-current-locations")
    public Map<UUID, Location> getAllUsersCurrentLocations() {
        LOGGER.info("Getting all users' current locations'");
        List<VisitedLocation> allUserCurrentLocations = tourGuideService.getAllUserCurrentLocations();
        Map<UUID, Location> result = new HashMap<>();
        for(VisitedLocation visitedLocation : allUserCurrentLocations) {
            result.put(visitedLocation.getUserId(), visitedLocation.getLocation());
        }
        return result;
    }

    /**
     * As administrator, get user's location for defined user.
     * @param username of the searched user
     * @return the last visited location of user - if user doesn't exist, throw ElementNotFoundException
     */
    @GetMapping("/users/current-location")
    public VisitedLocation getUserLocation(@RequestParam(name = "username") String username) {
        LOGGER.info("Getting user's current location for user : " + username);
        return tourGuideService.getUserCurrentLocation(username);
    }
}
