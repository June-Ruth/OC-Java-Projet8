package org.openclassrooms.tourguide.webapp.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openclassrooms.tourguide.models.model.location.Location;
import org.openclassrooms.tourguide.models.model.location.VisitedLocation;
import org.openclassrooms.tourguide.models.model.user.User;
import org.openclassrooms.tourguide.webapp.exception.ElementNotFoundException;
import org.openclassrooms.tourguide.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(AdminController.class)
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private static User user;
    private static final UUID uuid1 = UUID.randomUUID();
    private static VisitedLocation visitedLocation;
    private static List<VisitedLocation> visitedLocationList;

    @BeforeAll
    static void beforeAll() {
        user = new User(uuid1, "userName", "phoneNumber", "emailAddress");
        visitedLocation = new VisitedLocation(UUID.randomUUID(), new Location(2d, 2d), Date.from(Instant.now()));
        visitedLocationList = new ArrayList<>();
        visitedLocationList.add(visitedLocation);
    }

    // HOME PAGE TESTS //

    @Test
    void homePageTest() throws Exception {
        mockMvc.perform(get("/admin/home"))
                .andExpect(status().isOk());
    }

    // GET ALL USERS CURRENT LOCATIONS TESTS //

    @Test
    void getAllCurrentLocations() throws Exception  {
        when(userService.getAllUserCurrentLocations()).thenReturn(visitedLocationList);
        mockMvc.perform(get("/admin/users/all-current-locations"))
                .andExpect(status().isOk());
    }

    // GET USER LOCATION TESTS //

    @Test
    void getExistingUserLocation() throws Exception {
        when(userService.getUserCurrentLocation(anyString())).thenReturn(visitedLocation);
        mockMvc.perform(get("/admin/users/current-location?username=" + user.getUsername()))
                .andExpect(status().isOk());
    }

    @Test
    void getNonExistentUserLocation() throws Exception {
        when(userService.getUserCurrentLocation(anyString())).thenThrow(ElementNotFoundException.class);
        mockMvc.perform(get("/admin/users/current-location?username=" + user.getUsername()))
                .andExpect(status().isNotFound());
    }


}
