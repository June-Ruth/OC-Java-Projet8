package org.openclassrooms.tourguide.userprofile.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openclassrooms.tourguide.userprofile.dto.UserContactsDTO;
import org.openclassrooms.tourguide.models.model.User;
import org.openclassrooms.tourguide.models.model.UserPreferences;
import org.openclassrooms.tourguide.userprofile.exception.ElementNotFoundException;
import org.openclassrooms.tourguide.userprofile.service.UserService;
import org.openclassrooms.tourguide.userprofile.util.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    /* NB : here, we no test what happens in case of invalid data for updating user information or preferences
    because we don't have any information about what means valid or invalid.*/

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private static User user;
    private static UserContactsDTO userContactsDTO;
    private static UserPreferences userPreferences;
    private static final UUID uuid1 = UUID.randomUUID();

    @BeforeAll
    static void beforeAll() {
        user = new User(uuid1, "userName", "phoneNumber", "emailAddress");
        userContactsDTO = DtoConverter.convertUserToUserContactsDto(user);
        userPreferences = new UserPreferences();
    }

    // GET USER PROFILE TEST //

    @Test
    void getUserProfileWithExistingIdTest() throws Exception {
        when(userService.getUser(any(UUID.class))).thenReturn(user);
        mockMvc.perform(get("/profile/{userId}", uuid1))
                .andExpect(status().isOk());
    }

    @Test
    void getUserProfileWithNonExistentIdTest() throws Exception {
        when(userService.getUser(any(UUID.class))).thenThrow(ElementNotFoundException.class);
        mockMvc.perform(get("/profile/{userId}", uuid1))
                .andExpect(status().isNotFound());
    }

    // UPDATE USER CONTACTS TESTS //

    @Test
    void updateUserContactsWithExistingIdAndValidDataTest() throws Exception {
        when(userService.getUser(any(UUID.class))).thenReturn(user);
        when(userService.updateUser(any(User.class))).thenReturn(user);
        mockMvc.perform(put("/profile/{userId}", uuid1)
                .content(new ObjectMapper().writeValueAsString(userContactsDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateUserContactsWithNonExistentIdAndValidDataTest() throws Exception {
        when(userService.getUser(any(UUID.class))).thenThrow(ElementNotFoundException.class);
        mockMvc.perform(put("/profile/{userId}", uuid1)
                .content(new ObjectMapper().writeValueAsString(userContactsDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    // GET USER PREFERENCES TESTS //

    @Test
    void getUserPreferencesWithExistingIdTest() throws Exception {
        when(userService.getUserPreferences(any(UUID.class))).thenReturn(userPreferences);
        mockMvc.perform(get("/profile/{userId}/preferences", uuid1))
                .andExpect(status().isOk());
    }

    @Test
    void getUserPreferencesWithNonExistentIdTest() throws Exception {
        when(userService.getUserPreferences(any(UUID.class))).thenThrow(ElementNotFoundException.class);
        mockMvc.perform(get("/profile/{userId}/preferences", uuid1))
                .andExpect(status().isNotFound());
    }

    // UPDATE USER PREFERENCES TEST //

    @Test
    void updateUserPreferencesWithExistingIdAndValidDataTest() throws Exception {
        when(userService.getUser(any(UUID.class))).thenReturn(user);
        when(userService.updateUser(any(User.class))).thenReturn(user);
        mockMvc.perform(put("/profile/{userId}/preferences", uuid1)
                .content(new ObjectMapper().writeValueAsString(userPreferences))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateUserPreferencesWithNonExistentIdAndValidDataTest() throws Exception {
        when(userService.getUser(any(UUID.class))).thenThrow(ElementNotFoundException.class);
        mockMvc.perform(put("/profile/{userId}/preferences", uuid1)
                .content(new ObjectMapper().writeValueAsString(userPreferences))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
