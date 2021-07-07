package org.openclassrooms.tourguide.webapp.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openclassrooms.tourguide.models.model.user.User;
import org.openclassrooms.tourguide.models.model.user.UserPreferences;
import org.openclassrooms.tourguide.webapp.exception.ElementNotFoundException;
import org.openclassrooms.tourguide.webapp.service.TripsService;
import org.openclassrooms.tourguide.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(TripDealController.class)
public class TripDealControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TripsService tripsService;

    @MockBean
    private UserService userService;

    private static User user;
    private static final UUID uuid1 = UUID.randomUUID();

    @BeforeAll
    static void beforeAll() {
        user = new User(uuid1, "userName", "phoneNumber", "emailAddress",  Date.from(Instant.now()), new ArrayList<>(), new ArrayList<>(), new UserPreferences(), new ArrayList<>());
    }

    //GET TRIP DEALS TESTS //

    @Test
    void getTripDealsForExistingUserTest() throws Exception {
        when(userService.getUser(anyString())).thenReturn(user);
        when(tripsService.getTripDeals(any(User.class))).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/trip-deals?username=" + user.getUsername()))
                .andExpect(status().isOk());
    }

    @Test
    void getTripDealsForNonExistentUserTest() throws Exception {
        when(userService.getUser(anyString())).thenThrow(ElementNotFoundException.class);
        mockMvc.perform(get("/trip-deals?username=" + user.getUsername()))
                .andExpect(status().isNotFound());
    }

}
