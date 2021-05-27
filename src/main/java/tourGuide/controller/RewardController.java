package tourGuide.controller;

import com.jsoniter.output.JsonStream;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tourGuide.service.TourGuideService;

import java.util.UUID;

@RestController
public class RewardController {

    private final TourGuideService tourGuideService;

    public RewardController(final TourGuideService tourGuideService1) {
        tourGuideService = tourGuideService1;
    }

    @RequestMapping("/users/{userId}/rewards")
    public String getRewards(@PathVariable UUID userId, @RequestParam String username) {
        return JsonStream.serialize(tourGuideService.getUserRewards(tourGuideService.getUser(username)));
    }
}
