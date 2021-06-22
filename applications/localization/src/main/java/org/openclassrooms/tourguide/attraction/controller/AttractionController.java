package org.openclassrooms.tourguide.attraction.controller;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import org.openclassrooms.tourguide.attraction.dto.NearAttractionDto;
import org.openclassrooms.tourguide.attraction.service.AttractionService;
import org.openclassrooms.tourguide.attraction.util.DtoConverter;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public List<NearAttractionDto> getNearByAttractions(@RequestParam(name = "latitude") double userCurrentLatitude,
                                                        @RequestParam(name = "longitude") double userCurrentLongitude) {

        /* Instead: Get the closest five tourist attractions to the user - no matter how far away they are.
        // Return a new JSON object that contains:
        // Name of Tourist attraction,
        // Tourist attractions lat/long,
        // The user's location lat/long,
        // The distance in miles between the user's location and each of the attractions.
        // The reward points for visiting each Attraction.
        // Note: Attraction reward points can be gathered from RewardsCentral*/
        Location userCurrentLocation = new Location(userCurrentLatitude, userCurrentLongitude);
        List<Attraction> fiveNearestAttractions = attractionService.getFiveNearestAttractions(userCurrentLocation);
        List<NearAttractionDto> fiveNearestAttractionsDto = new ArrayList<>();

        for(Attraction attraction : fiveNearestAttractions) {
            int rewardPoints = attractionService.getAttractionRewardPoints(attraction);
            NearAttractionDto nearAttractionDTO = DtoConverter.convertAttractionToNearAttractionDto(attraction, userCurrentLocation, rewardPoints);
            fiveNearestAttractionsDto.add(nearAttractionDTO);
        }
        return fiveNearestAttractionsDto;
    }

}
