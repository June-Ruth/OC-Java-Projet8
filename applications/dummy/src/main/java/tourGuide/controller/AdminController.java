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
public class AdminController {

    private final TourGuideService tourGuideService;

    public AdminController(final TourGuideService tourGuideService1) {
        tourGuideService = tourGuideService1;
    }

    @RequestMapping("/admin/users-current-locations")
    public String getAllCurrentLocations() {
        // TODO: Get a list of every user's most recent location as JSON
        //- Note: does not use gpsUtil to query for their current location,
        //        but rather gathers the user's current location from their stored location history.
        //
        // Return object should be the just a JSON mapping of userId to Locations similar to:
        //     {
        //        "019b04a9-067a-4c76-8817-ee75088c3822": {"longitude":-48.188821,"latitude":74.84371}
        //        ...
        //     }

        return JsonStream.serialize("");
    }

    @RequestMapping("/admin/users/{userId}")
    public String getUser(@PathVariable UUID userId) {
        //TODO
        return null;
    }

    @RequestMapping("/admin/users/{userId}/current-location")
    public String getLocation(@PathVariable UUID userId, @RequestParam String userName) throws ExecutionException, InterruptedException {
        VisitedLocation visitedLocation = tourGuideService.getUserLocation(tourGuideService.getUser(userName));
        return JsonStream.serialize(visitedLocation.location);
    }

    @RequestMapping("/admin/users/{userId}/rewards")
    public String getRewards(@PathVariable UUID userId, @RequestParam String username) {
        return JsonStream.serialize(tourGuideService.getUserRewards(tourGuideService.getUser(username)));
    }

}
