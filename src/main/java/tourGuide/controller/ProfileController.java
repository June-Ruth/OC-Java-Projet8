package tourGuide.controller;

import com.jsoniter.output.JsonStream;
import gpsUtil.location.VisitedLocation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tourGuide.service.TourGuideService;

import java.util.UUID;

@RestController
public class ProfileController {

    private final TourGuideService tourGuideService;

    public ProfileController(final TourGuideService tourGuideService1) {
        tourGuideService = tourGuideService1;
    }

    @RequestMapping("/users/{userId}/profile")
    public String getUser(@PathVariable UUID userId) {
        //TODO
        return null;
    }

    @RequestMapping("/users/{userId}/profile/current-location")
    public String getLocation(@PathVariable UUID userId, @RequestParam String userName) {
        VisitedLocation visitedLocation = tourGuideService.getUserLocation(tourGuideService.getUser(userName));
        return JsonStream.serialize(visitedLocation.location);
    }
}
