package org.openclassrooms.tourguide.webapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openclassrooms.tourguide.models.model.user.User;
import org.openclassrooms.tourguide.models.model.user.UserPreferences;
import org.openclassrooms.tourguide.models.model.user.UserReward;
import org.openclassrooms.tourguide.webapp.exception.ElementNotFoundException;
import org.openclassrooms.tourguide.webapp.service.LocationService;
import org.openclassrooms.tourguide.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(ProfileController.class)
public class ProfileControllerTest {

    /* NB : here, we no test what happens in case of invalid data for updating user information or preferences
    because we don't have any information about what means valid or invalid.*/

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private LocationService locationService;

    private static User user;
    private static final UUID uuid1 = UUID.randomUUID();
    private static UserPreferences userPreferences;
    private static List<UserReward> userRewards;

    @BeforeAll
    static void beforeAll() {
        user = new User(uuid1, "userName", "phoneNumber", "emailAddress");
        userPreferences = new UserPreferences();
        userRewards = new ArrayList<>();
    }

    // GET USER PROFILE TESTS //

    @Test
    void getUserProfileWithExistingUsernameTest() throws Exception {
        when(userService.getUser(anyString())).thenReturn(user);
        mockMvc.perform(get("/profile?username=" + user.getUsername()))
                .andExpect(status().isOk());
    }

    @Test
    void getUserProfileWithNonExistentUsernameTest() throws Exception {
        when(userService.getUser(anyString())).thenThrow(ElementNotFoundException.class);
        mockMvc.perform(get("/profile?username=" + user.getUsername()))
                .andExpect(status().isNotFound());
    }

    // GET USER PREFERENCES TESTS //

    @Test
    void getUserPreferencesWithExistingUsernameTest() throws Exception {
        when(userService.getUserPreferences(anyString())).thenReturn(userPreferences);
        mockMvc.perform(get("/profile/preferences?username=" + user.getUsername()))
                .andExpect(status().isOk());
    }

    @Test
    void getUserPreferencesWithNonExistentUsernameTest() throws Exception {
        when(userService.getUserPreferences(anyString())).thenThrow(ElementNotFoundException.class);
        mockMvc.perform(get("/profile/preferences?username=" + user.getUsername()))
                .andExpect(status().isNotFound());
    }

    // UPDATE USER PREFERENCES TESTS //

    @Test
    void updateUserPreferencesWithExistingUsernameAndValidDataTest() throws Exception {
        when(userService.updateUserPreferences(anyString(),any(UserPreferences.class))).thenReturn(userPreferences);
        mockMvc.perform(put("/profile/preferences?username=" + user.getUsername())
                .content(new ObjectMapper().writeValueAsString(userPreferences))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateUserPreferencesWithNonExistentUsernameAndValidDataTest() throws Exception {
        when(userService.updateUserPreferences(anyString(),any(UserPreferences.class))).thenThrow(ElementNotFoundException.class);
        mockMvc.perform(put("/profile/preferences?username=" + user.getUsername())
                .content(new ObjectMapper().writeValueAsString(userPreferences))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    // GET USER PREFERENCES TESTS //

    @Test
    void getUserRewardsWithExistingUsernameTest() throws Exception {
        when(userService.getUserRewards(anyString())).thenReturn(userRewards);
        mockMvc.perform(get("/profile/rewards?username=" + user.getUsername()))
                .andExpect(status().isOk());
    }

    @Test
    void getUserRewardsWithNonExistentUsernameTest() throws Exception {
        when(userService.getUserRewards(anyString())).thenThrow(ElementNotFoundException.class);
        mockMvc.perform(get("/profile/rewards?username=" + user.getUsername()))
                .andExpect(status().isNotFound());
    }
}
