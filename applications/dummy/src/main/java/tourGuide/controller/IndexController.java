package tourGuide.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    public IndexController() {}

    @RequestMapping("/")
    public String index() {
        return "Greetings from TourGuide!";
    }
}