package tourGuide.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tourGuide.service.TourGuideService;
import tripPricer.Provider;

import java.util.List;
import java.util.UUID;

@RestController
public class TripDealController {

   /* private final TourGuideService tourGuideService;

    public TripDealController(final TourGuideService tourGuideService1) {
        tourGuideService = tourGuideService1;
    }

    @RequestMapping("/users/{userId}/trip-deals")
    public String getTripDeals(@PathVariable UUID userId, @RequestParam String username) {
        List<Provider> providers = tourGuideService.getTripDeals(tourGuideService.getUser(username));
        return "";//JsonStream.serialize(providers);
    }*/

}
