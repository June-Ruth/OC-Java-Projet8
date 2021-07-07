package org.openclassrooms.tourguide.webapp.controller;

import org.openclassrooms.tourguide.models.model.trip.Provider;
import org.openclassrooms.tourguide.models.model.user.User;
import org.openclassrooms.tourguide.webapp.service.TripsService;
import org.openclassrooms.tourguide.webapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TripDealController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TripDealController.class);

    private final TripsService tripsService;

    private final UserService userService;

    public TripDealController(final TripsService tripsService1,
                              final UserService userService1) {
        tripsService = tripsService1;
        userService = userService1;
    }

    /**
     * As authenticated user, get trip deals proposals.
     * Authentication is not implemented yet.
     * @param username of the authenticated user
     * @return list of provider - if user doesn't exist, throw ElementNotFoundException
     */
    @GetMapping("/trip-deals")
    public List<Provider> getTripDeals(@RequestParam(name = "username") final String username) {
        LOGGER.info("Getting trips deals for user : " + username);
        User user = userService.getUser(username);
        return tripsService.getTripDeals(user);
    }
}
