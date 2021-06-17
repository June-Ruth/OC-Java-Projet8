package org.openclassrooms.tourguide.localization.controller;

import gpsUtil.location.Attraction;
import org.openclassrooms.tourguide.localization.service.AttractionService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttractionController {

    private AttractionService attractionService;

    public AttractionController(final AttractionService attractionService1) {
        attractionService = attractionService1;
    }

    @RequestMapping("/attractions/{attractionName}")
    public Attraction getAttractionInformation(@PathVariable String attractionName) {
        return attractionService.getAttraction(attractionName);
    }

    @RequestMapping("/attractions/closest-five")
    public String getNearbyAttractions(@RequestParam String username) {

        //TODO : Instead: Get the closest five tourist attractions to the user - no matter how far away they are.
        // Return a new JSON object that contains:
        // Name of Tourist attraction,
        // Tourist attractions lat/long,
        // The user's location lat/long,
        // The distance in miles between the user's location and each of the attractions.
        // The reward points for visiting each Attraction.
        // Note: Attraction reward points can be gathered from RewardsCentral


        /*VisitedLocation visitedLocation = tourGuideService.getUserLocation(tourGuideService.getUser(username));
        return "";//JsonStream.serialize(tourGuideService.getNearByAttractions(visitedLocation));*/

        return null;
    }

}
