package org.openclassrooms.tourguide.gpsapi.controller;

import org.openclassrooms.tourguide.gpsapi.service.GpsService;
import org.openclassrooms.tourguide.models.model.location.Attraction;
import org.openclassrooms.tourguide.models.model.location.VisitedLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public Attraction getAttractionInformation(@PathVariable final String attractionName) {
        LOGGER.info("Getting attraction " + attractionName);
        return gpsService.getAttraction(attractionName);
    }

    /**
     * Get all attractions.
     * @return list of attractions
     */
    @GetMapping("/attractions")
    public List<Attraction> getAllAttractions() {
        LOGGER.info("Getting all attractions");
        return gpsService.getAllAttractions();
    }

    /**
     * Get user actual location from gps.
     * @param userId searched
     * @return Visited location
     */
    @GetMapping("/location/{userId}")
    public VisitedLocation getUserLocation(@PathVariable final UUID userId) {
        LOGGER.info("Getting location for user : " + userId);
        return gpsService.getUserActualLocation(userId);
    }
}
