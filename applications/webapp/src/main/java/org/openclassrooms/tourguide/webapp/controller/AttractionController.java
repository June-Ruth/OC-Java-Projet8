package org.openclassrooms.tourguide.webapp.controller;

import org.openclassrooms.tourguide.models.model.location.Attraction;
import org.openclassrooms.tourguide.models.model.location.Location;
import org.openclassrooms.tourguide.models.model.user.User;
import org.openclassrooms.tourguide.webapp.dto.NearAttractionDto;
import org.openclassrooms.tourguide.webapp.service.LocationService;
import org.openclassrooms.tourguide.webapp.service.RewardsService;
import org.openclassrooms.tourguide.webapp.service.UserService;
import org.openclassrooms.tourguide.webapp.util.DtoConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class AttractionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttractionController.class);

    private final UserService userService;

    private final LocationService locationService;

    private final RewardsService rewardsService;

    public AttractionController(final UserService userService1,
                                final LocationService locationService1,
                                final RewardsService rewardsService1) {
        userService = userService1;
        locationService = locationService1;
        rewardsService = rewardsService1;
    }

    /**
     * Access to attraction information.
     * @param attractionName of the searched attraction
     * @return attraction - if attraction doesn't exist, throw ElementNotFoundException
     */
    @GetMapping("/attractions")
    public Attraction getAttractionInformation(@RequestParam(name = "attractionName") final String attractionName) {
        LOGGER.info("Getting attraction with name : " + attractionName);
        return locationService.getAttraction(attractionName);
    }

    /**
     * As authenticated user, get the closest five attractions to the user, no matter how far away they are.
     * Authentication is not implemented yet.
     * @param username of the authenticated user - if user doesn't exist, throw ElementNotFoundException
     * @return nearAttractionDto
     * @see NearAttractionDto
     */
    @GetMapping("/attractions/closest-five")
    public List<NearAttractionDto> getAttractionProposals(@RequestParam(name = "username") final String username) {
        LOGGER.info("Getting attraction proposals for user : " + username);

        User user = userService.getUser(username);

        Location userCurrentLocation = userService.getUserCurrentLocation(username).getLocation();

        Map<Double, Attraction> fiveClosestAttractions = locationService.getFiveClosestAttractionsWithDistance(userCurrentLocation);
        List<NearAttractionDto> fiveClosestAttractionsDto = new ArrayList<>();

        fiveClosestAttractions.forEach((distance, attraction) -> {
            int rewardPoints = rewardsService.getAttractionRewardPoints(attraction, user);
            NearAttractionDto nearAttractionDTO = DtoConverter.convertAttractionToNearAttractionDto(attraction, userCurrentLocation, rewardPoints, distance);
            fiveClosestAttractionsDto.add(nearAttractionDTO);
        });

        return fiveClosestAttractionsDto;
    }
}
