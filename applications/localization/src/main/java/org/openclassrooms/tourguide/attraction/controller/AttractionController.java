package org.openclassrooms.tourguide.attraction.controller;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import org.openclassrooms.tourguide.attraction.service.AttractionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AttractionController {

    private AttractionService attractionService;

    public AttractionController(final AttractionService attractionService1) {
        attractionService = attractionService1;
    }

    @GetMapping("/attractions/{attractionName}")
    public Attraction getAttractionInformation(@PathVariable String attractionName) {
        return attractionService.getAttraction(attractionName);
    }

    @GetMapping("/attractions/closest-five")
    public List<Attraction> getFiveClosestAttractions(@RequestParam(name = "latitude") double userCurrentLatitude,
                                                      @RequestParam(name = "longitude") double userCurrentLongitude) {
        Location userCurrentLocation = new Location(userCurrentLatitude, userCurrentLongitude);
        List<Attraction> fiveNearestAttractions = attractionService.getFiveNearestAttractions(userCurrentLocation);

        return fiveNearestAttractions;
    }

}
