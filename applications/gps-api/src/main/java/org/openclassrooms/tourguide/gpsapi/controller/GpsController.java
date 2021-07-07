package org.openclassrooms.tourguide.gpsapi.controller;

import org.openclassrooms.tourguide.gpsapi.service.GpsService;
import org.openclassrooms.tourguide.models.model.location.Attraction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * Get all attractions.
     * @return list of attractions
     */
    @GetMapping("/attractions")
    public List<Attraction> getAllAttractions() {
        LOGGER.info("Getting all attractions");
        return gpsService.getAllAttractions();
    }
}
