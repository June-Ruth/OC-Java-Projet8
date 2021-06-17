package org.openclassrooms.tourguide.userprofile.controller;

import org.openclassrooms.tourguide.userprofile.dto.UserContactsDTO;
import org.openclassrooms.tourguide.models.model.User;
import org.openclassrooms.tourguide.models.model.UserPreferences;
import org.openclassrooms.tourguide.userprofile.service.UserService;
import org.openclassrooms.tourguide.userprofile.util.DtoConverter;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserService userService;

    public UserController(final UserService userService1) {
        userService = userService1;
    }

    @RequestMapping("/profile/{username}")
    public UserContactsDTO getUserProfile(@PathVariable final String username) {
        User user = userService.getUser(username);
        UserContactsDTO userContactsDTO = DtoConverter.convertUserToUserContactsDto(user);
        return userContactsDTO;
    }

    @PutMapping("/profile/{username}")
    public UserContactsDTO updateUserContacts(@PathVariable final String username,
                                              @RequestBody final UserContactsDTO userContactsDTO) {
        User user = userService.getUser(username);
        user.setPhoneNumber(userContactsDTO.getPhoneNumber());
        user.setEmailAddress(userContactsDTO.getEmailAddress());
        userService.updateUser(user);
        return userContactsDTO;
    }

    @RequestMapping("/profile/{username}/preferences")
    public UserPreferences getUserPreferences(@PathVariable final String username) {
        UserPreferences userPreferences = userService.getUserPreferences(username);
        return userPreferences;
    }

    @PutMapping("/profile/{username}/preferences")
    public UserPreferences updateUserPreferences(@PathVariable final String username,
                                                 @RequestBody final UserPreferences updatedUserPreferences) {
        User user = userService.getUser(username);
        user.setUserPreferences(updatedUserPreferences);
        userService.updateUser(user);
        return updatedUserPreferences;
    }
}
