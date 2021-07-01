package org.openclassrooms.tourguide.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    public HomeController() {

    }

    /**
     * Home Page for current user on the app.
     * @return home message
     */
    @GetMapping("/home")
    public String homePage() {
        LOGGER.info("Getting home page.");
        return "Greetings from TourGuide Enterprise ! Welcome User.";
    }
}
