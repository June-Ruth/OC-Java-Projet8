package org.openclassrooms.tourguide.gpsapi.controller;

import org.openclassrooms.tourguide.gpsapi.service.GpsService;
import org.openclassrooms.tourguide.models.model.location.Attraction;
import org.openclassrooms.tourguide.models.model.location.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class GpsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GpsController.class);

    private final GpsService gpsService;

    public GpsController(final GpsService gpsService1) {
        gpsService = gpsService1;
    }

    /**
     * Access to attraction information.
     * @param attractionName of the searched attraction
     * @return attraction - if attraction doesn't exist, throw ElementNotFoundException
     */
    @GetMapping("/attractions/{attractionName}")
    public Attraction getAttractionInformation(@PathVariable String attractionName) {
        LOGGER.info("Getting attraction " + attractionName);
        return gpsService.getAttraction(attractionName);
    }

    /**
     * Get the closest five attractions to the userLocation, no matter how far away they are.
     * @param userCurrentLocation as Location
     * @return list of attraction
     */
    @GetMapping("/attractions/closest-five")
    public Map<Double, Attraction> getFiveClosestAttractions(@RequestBody Location userCurrentLocation) {
        LOGGER.info("Getting closest attraction from " + userCurrentLocation);
        Map<Double, Attraction> fiveNearestAttractions = gpsService.getFiveNearestAttractions(userCurrentLocation);
        return fiveNearestAttractions;
    }
}
