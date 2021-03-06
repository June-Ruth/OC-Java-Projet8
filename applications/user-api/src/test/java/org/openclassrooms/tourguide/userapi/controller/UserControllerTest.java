package org.openclassrooms.tourguide.userapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openclassrooms.tourguide.models.model.location.Location;
import org.openclassrooms.tourguide.models.model.location.VisitedLocation;
import org.openclassrooms.tourguide.models.model.user.User;
import org.openclassrooms.tourguide.models.model.user.UserPreferences;
import org.openclassrooms.tourguide.userapi.exception.ElementNotFoundException;
import org.openclassrooms.tourguide.userapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(UserController.class)
public class UserControllerTest {

    /* NB : here, we no test what happens in case of invalid data for updating user information or preferences
    because we don't have any information about what means valid or invalid.*/

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private static User user;
    private static UserPreferences userPreferences;
    private static final UUID uuid1 = UUID.randomUUID();
    private static VisitedLocation visitedLocation;
    private static List<VisitedLocation> visitedLocationList;
    private static List<User> userList;

    @BeforeAll
    static void beforeAll() {
        user = new User(uuid1, "userName", "phoneNumber", "emailAddress",  Date.from(Instant.now()), new ArrayList<>(), new ArrayList<>(), new UserPreferences(), new ArrayList<>());
        userPreferences = new UserPreferences();
        visitedLocation = new VisitedLocation(UUID.randomUUID(), new Location(2d, 2d), Date.from(Instant.now()));
        visitedLocationList = new ArrayList<>();
        visitedLocationList.add(visitedLocation);
        userList = new ArrayList<>();
        userList.add(user);
    }

    // GET USER PROFILE TESTS //

    @Test
    void getUserProfileWithExistingUsernameTest() throws Exception {
        when(userService.getUser(anyString())).thenReturn(user);
        mockMvc.perform(get("/users/{username}", user.getUsername()))
                .andExpect(status().isOk());
    }

    @Test
    void getUserProfileWithNonExistentUsernameTest() throws Exception {
        when(userService.getUser(anyString())).thenThrow(ElementNotFoundException.class);
        mockMvc.perform(get("/users/{username}", user.getUsername()))
                .andExpect(status().isNotFound());
    }

    // GET ALL USERS TESTS //

    @Test
    void getAllUsersTest() throws Exception {
        when(userService.getAllUsers()).thenReturn(userList);
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    // GET USER PREFERENCES TESTS //

    @Test
    void getUserPreferencesWithExistingUsernameTest() throws Exception {
        when(userService.getUser(anyString())).thenReturn(user);
        mockMvc.perform(get("/users/{username}/preferences", user.getUsername()))
                .andExpect(status().isOk());
    }

    @Test
    void getUserPreferencesWithNonExistentUsernameTest() throws Exception {
        when(userService.getUser(anyString())).thenThrow(ElementNotFoundException.class);
        mockMvc.perform(get("/users/{username}/preferences", user.getUsername()))
                .andExpect(status().isNotFound());
    }

    // UPDATE USER PREFERENCES TEST //

    @Test
    void updateUserPreferencesWithExistingUsernameAndValidDataTest() throws Exception {
        when(userService.getUser(anyString())).thenReturn(user);
        when(userService.updateUser(any(User.class))).thenReturn(user);
        mockMvc.perform(put("/users/{username}/preferences", user.getUsername())
                .content(new ObjectMapper().writeValueAsString(userPreferences))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateUserPreferencesWithNonExistentUsernameAndValidDataTest() throws Exception {
        when(userService.getUser(anyString())).thenThrow(ElementNotFoundException.class);
        mockMvc.perform(put("/users/{username}/preferences", user.getUsername())
                .content(new ObjectMapper().writeValueAsString(userPreferences))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    // GET ALL CURRENT LOCATIONS TESTS //

    @Test
    void getAllCurrentLocations() throws Exception  {
        when(userService.getAllUserCurrentLocations()).thenReturn(visitedLocationList);
        mockMvc.perform(get("/users/all-current-locations"))
                .andExpect(status().isOk());
    }


    // GET USER LOCATION TESTS //

    @Test
    void getExistingUserLocation() throws Exception {
        when(userService.getUserCurrentLocation(anyString())).thenReturn(visitedLocation);
        mockMvc.perform(get("/users/{username}/current-location", user.getUsername()))
                .andExpect(status().isOk());
    }

    @Test
    void getNonExistentUserLocation() throws Exception {
        when(userService.getUserCurrentLocation(anyString())).thenThrow(ElementNotFoundException.class);
        mockMvc.perform(get("/users/{username}/current-location", user.getUsername()))
                .andExpect(status().isNotFound());
    }

    // GET USER REWARDS TESTS //

    @Test
    void getExistingUserRewards() throws Exception {
        when(userService.getUser(anyString())).thenReturn(user);
        mockMvc.perform(get("/users/{username}/rewards", user.getUsername()))
                .andExpect(status().isOk());
    }

    @Test
    void getNonExistentUserRewards() throws Exception {
        when(userService.getUser(anyString())).thenThrow(ElementNotFoundException.class);
        mockMvc.perform(get("/users/{username}/rewards", user.getUsername()))
                .andExpect(status().isNotFound());
    }
}
