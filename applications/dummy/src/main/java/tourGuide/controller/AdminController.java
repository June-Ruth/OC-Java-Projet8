package tourGuide.controller;

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



    @RequestMapping("/admin/users/{userId}")
    public String getUser(@PathVariable UUID userId) {
        //TODO
        return null;
    }



    @RequestMapping("/admin/users/{userId}/rewards")
    public String getRewards(@PathVariable UUID userId, @RequestParam String username) {
        return null; //JsonStream.serialize(tourGuideService.getUserRewards(tourGuideService.getUser(username)));
    }

}
