package org.openclassrooms.tourguide.userprofile.controller;

import org.openclassrooms.tourguide.userprofile.dto.UserContactsDTO;
import org.openclassrooms.tourguide.models.model.User;
import org.openclassrooms.tourguide.models.model.UserPreferences;
import org.openclassrooms.tourguide.userprofile.service.UserService;
import org.openclassrooms.tourguide.userprofile.util.DtoConverter;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {

    private UserService userService;

    public UserController(final UserService userService1) {
        userService = userService1;
    }

    @RequestMapping("/profile/{userId}")
    public UserContactsDTO getUserProfile(@PathVariable final UUID userId) {
        User user = userService.getUser(userId);
        UserContactsDTO userContactsDTO = DtoConverter.convertUserToUserContactsDto(user);
        return userContactsDTO;
    }

    @PutMapping("/profile/{userId}")
    public UserContactsDTO updateUserContacts(@PathVariable final UUID userId,
                                              @RequestBody final UserContactsDTO userContactsDTO) {
        User user = userService.getUser(userId);
        user.setPhoneNumber(userContactsDTO.getPhoneNumber());
        user.setEmailAddress(userContactsDTO.getEmailAddress());
        userService.updateUser(user);
        return userContactsDTO;
    }

    @RequestMapping("/profile/{userId}/preferences")
    public UserPreferences getUserPreferences(@PathVariable final UUID userId) {
        UserPreferences userPreferences = userService.getUserPreferences(userId);
        return userPreferences;
    }

    @PutMapping("/profile/{userId}/preferences")
    public UserPreferences updateUserPreferences(@PathVariable final UUID userId,
                                                 @RequestBody final UserPreferences updatedUserPreferences) {
        User user = userService.getUser(userId);
        user.setUserPreferences(updatedUserPreferences);
        userService.updateUser(user);
        return updatedUserPreferences;
    }
}
