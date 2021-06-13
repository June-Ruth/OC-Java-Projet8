package tourGuide.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserController {

    public UserController() { }

    @RequestMapping("/users/{userId}")
    public String userHome(@PathVariable UUID userId) {
        //TODO : return User Principal Information HomePage
        return null;
    }

    //TODO : modify UserPreferences + get
    // "/users/{userId}/preferences"
}
