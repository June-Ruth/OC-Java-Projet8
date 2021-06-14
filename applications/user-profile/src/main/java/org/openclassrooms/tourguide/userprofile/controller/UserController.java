package org.openclassrooms.tourguide.userprofile.controller;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {

    public UserController() {

    }

    @RequestMapping("profile/{userId}")
    public String getUserProfile(@PathVariable UUID userId) {
        //TODO : afficher les principales informations de profil
        //userService.getUser(userId)
        return null;
    }

    @PostMapping("profile/{userId}")
    public String updateUserProfileInformation(@PathVariable UUID userId) {
        //TODO : Modifier ses informations de profil modifiables
        //userService.update(userId, userUpdated)
        return null;
    }

    @RequestMapping("profile/{userId}/preferences")
    public String getUserPreferences(@PathVariable UUID userId) {
        //TODO : Afficher les préférences de l'utilisateur en matière de voyages
        //userService.
        return null;
    }

    @PostMapping("profile/{userId}/preferences")
    public String updateUserPreferences(@PathVariable UUID userId) {
        //TODO : Modifier les préférences de l'utilisateur
        return null;
    }


}
