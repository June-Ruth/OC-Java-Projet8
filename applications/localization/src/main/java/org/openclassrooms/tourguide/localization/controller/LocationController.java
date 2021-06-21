package org.openclassrooms.tourguide.localization.controller;

import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import org.openclassrooms.tourguide.localization.service.LocationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class LocationController {

    private LocationService locationService;

    public LocationController(final LocationService locationService1) {
        locationService = locationService1;
    }


    // Note: does not use gpsUtil to query for their current location,
    //        but rather gathers the user's current location from their stored location history.
    // Return object should be the just a JSON mapping of userId to Locations similar to:
    // { "019b04a9-067a-4c76-8817-ee75088c3822": {"longitude":-48.188821,"latitude":74.84371} ... }
    @RequestMapping("/location/users")
    public Map<UUID, Location> getAllCurrentLocations() {
        List<VisitedLocation> allUserCurrentLocations = locationService.getAllUserCurrentLocations();

        Map<UUID, Location> result = new HashMap<>();

        for(VisitedLocation visitedLocation : allUserCurrentLocations) {
            result.put(visitedLocation.userId, visitedLocation.location);
        }
        return result;
    }

    @RequestMapping("/location")
    public Location getUserLocation(@RequestParam(name = "username") String username) {
        VisitedLocation lastVisitedLocation = locationService.getUserCurrentLocation(username);
        return lastVisitedLocation.location;
    }
}
