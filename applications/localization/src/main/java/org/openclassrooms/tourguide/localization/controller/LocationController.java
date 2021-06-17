package org.openclassrooms.tourguide.localization.controller;

import gpsUtil.location.VisitedLocation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LocationController {

    public LocationController() {

    }


    @RequestMapping("/location/users")
    public List<VisitedLocation> getAllCurrentLocations() {

        //TODO: Get a list of every user's most recent location as JSON
        // Note: does not use gpsUtil to query for their current location,
        //        but rather gathers the user's current location from their stored location history.
        // Return object should be the just a JSON mapping of userId to Locations similar to:
        // { "019b04a9-067a-4c76-8817-ee75088c3822": {"longitude":-48.188821,"latitude":74.84371} ... }

        return null;
    }

    @RequestMapping("/location/")
    public VisitedLocation getUserLocation(@RequestParam String username) {
        //TODO : Get user location

        /*   VisitedLocation visitedLocation = tourGuideService.getUserLocation(tourGuideService.getUser(userName));
        return null; //JsonStream.serialize(visitedLocation.location);*/

        return null;
    }
}
