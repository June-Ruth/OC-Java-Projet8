package org.openclassrooms.tourguide.webapp.controller;

import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import org.openclassrooms.tourguide.webapp.service.TourGuideService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/admin")
@RestController
public class AdminWebController {

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
    @GetMapping("/users/all-current-locations")
    public Map<UUID, Location> getAllUsersCurrentLocations() {
        List<VisitedLocation> allUserCurrentLocations = tourGuideService.getAllUserCurrentLocations();
        Map<UUID, Location> result = new HashMap<>();
        for(VisitedLocation visitedLocation : allUserCurrentLocations) {
            result.put(visitedLocation.userId, visitedLocation.location);
        }
        return result;
    }

    @GetMapping("/users/current-location")
    public VisitedLocation getUserLocation(@RequestParam(name = "username") String username) {
        return tourGuideService.getUserCurrentLocation(username);
    }
}
