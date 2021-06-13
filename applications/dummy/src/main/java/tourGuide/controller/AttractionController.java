package tourGuide.controller;

import com.jsoniter.output.JsonStream;
import gpsUtil.location.VisitedLocation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tourGuide.service.TourGuideService;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
public class AttractionController {

    private final TourGuideService tourGuideService;

    public AttractionController(final TourGuideService tourGuideService1) {
        tourGuideService = tourGuideService1;
    }

    //  TODO: Change this method to no longer return a List of Attractions.
    //  Instead: Get the closest five tourist attractions to the user - no matter how far away they are.
    //  Return a new JSON object that contains:
    // Name of Tourist attraction,
    // Tourist attractions lat/long,
    // The user's location lat/long,
    // The distance in miles between the user's location and each of the attractions.
    // The reward points for visiting each Attraction.
    //    Note: Attraction reward points can be gathered from RewardsCentral
    @RequestMapping("/users/{userId}/attractions/closest-five")
    public String getNearbyAttractions(@PathVariable UUID userId, @RequestParam String username) throws ExecutionException, InterruptedException {
        VisitedLocation visitedLocation = tourGuideService.getUserLocation(tourGuideService.getUser(username));
        return JsonStream.serialize(tourGuideService.getNearByAttractions(visitedLocation));
    }

    //TODO : obtenir les infos sur les attractions
}
