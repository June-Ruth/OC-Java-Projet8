package org.openclassrooms.tourguide.tripapi.controller;

import org.openclassrooms.tourguide.models.model.trip.Provider;
import org.openclassrooms.tourguide.models.model.user.User;
import org.openclassrooms.tourguide.tripapi.service.TripService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class TripController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TripController.class);

    private TripService tripService;

    public TripController(final TripService tripService1) {
        tripService = tripService1;
    }

    /**
     * Get trip deal for specified user.
     * @param username of user
     * @param userId of user
     * @param numberOfAdults set in userPreferences
     * @param numberOfChildren set in userPreferences
     * @param tripDuration set in userPreferences
     * @param cumulativeRewardPoints of user rewards
     * @return list of providers
     */
    @GetMapping("/trips/{username}")
    public List<Provider> getTripDeals(@PathVariable final String username,
                                       @RequestParam final UUID userId,
                                       @RequestParam final int numberOfAdults,
                                       @RequestParam final int numberOfChildren,
                                       @RequestParam final int tripDuration,
                                       @RequestParam final int cumulativeRewardPoints) {
        LOGGER.info("Getting trips deals for user : " + username);
        return tripService.getTripDeals(userId, numberOfAdults, numberOfChildren, tripDuration, cumulativeRewardPoints);
    }





}
