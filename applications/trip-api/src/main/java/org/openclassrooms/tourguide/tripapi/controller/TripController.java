package org.openclassrooms.tourguide.tripapi.controller;

import org.openclassrooms.tourguide.models.model.trip.Provider;
import org.openclassrooms.tourguide.models.model.user.User;
import org.openclassrooms.tourguide.tripapi.service.TripService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TripController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TripController.class);

    private TripService tripService;

    public TripController(final TripService tripService1) {
        tripService = tripService1;
    }

    /**
     * Get trip deal for specified user.
     * @param username of specified user
     * @param user specified
     * @return list of providers
     */
    @GetMapping("/trips/{username}")
    public List<Provider> getTripDeals(@PathVariable final String username,
                                       @RequestBody User user) {
        LOGGER.info("Getting trips deals for user : " + username);
        return tripService.getTripDeals(user);
    }





}
