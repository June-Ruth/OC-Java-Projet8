package org.openclassrooms.tourguide.localization.controller;

import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.openclassrooms.tourguide.localization.dto.NearAttractionDTO;
import org.openclassrooms.tourguide.localization.service.LocationService;
import org.openclassrooms.tourguide.localization.util.DtoConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AttractionController {

    private LocationService locationService;

    public AttractionController(final LocationService locationService1) {
        locationService = locationService1;
    }

    @RequestMapping("/attractions/{attractionName}")
    public Attraction getAttractionInformation(@PathVariable String attractionName) {
        return locationService.getAttraction(attractionName);
    }

    @RequestMapping("/attractions/closest-five")
    public List<NearAttractionDTO> getNearByAttractions(@RequestParam(name = "username") String username) {

        /* Instead: Get the closest five tourist attractions to the user - no matter how far away they are.
        // Return a new JSON object that contains:
        // Name of Tourist attraction,
        // Tourist attractions lat/long,
        // The user's location lat/long,
        // The distance in miles between the user's location and each of the attractions.
        // The reward points for visiting each Attraction.
        // Note: Attraction reward points can be gathered from RewardsCentral*/

        VisitedLocation userCurrentLocation = locationService.getUserCurrentLocation(username);

        List<Attraction> fiveNearestAttractions = locationService.getFiveNearestAttractions(userCurrentLocation);
        List<NearAttractionDTO> fiveNearestAttractionsDto = new ArrayList<>();

        for(Attraction attraction : fiveNearestAttractions) {
            int rewardPoints = locationService.getAttractionRewardPoints(attraction);
            NearAttractionDTO nearAttractionDTO = DtoConverter.convertAttractionToNearAttractionDto(attraction, userCurrentLocation, rewardPoints);
            fiveNearestAttractionsDto.add(nearAttractionDTO);
        }
        return fiveNearestAttractionsDto;
    }

}
