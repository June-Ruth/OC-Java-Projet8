package org.openclassrooms.tourguide.webapp.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openclassrooms.tourguide.models.model.location.Attraction;
import org.openclassrooms.tourguide.models.model.location.Location;
import org.openclassrooms.tourguide.models.model.location.VisitedLocation;
import org.openclassrooms.tourguide.models.model.user.User;
import org.openclassrooms.tourguide.webapp.exception.ElementNotFoundException;
import org.openclassrooms.tourguide.webapp.service.LocationService;
import org.openclassrooms.tourguide.webapp.service.RewardsService;
import org.openclassrooms.tourguide.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(AttractionController.class)
public class AttractionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private LocationService locationService;

    @MockBean
    private RewardsService rewardsService;

    private static User user;
    private static final UUID uuid1 = UUID.randomUUID();
    private static Attraction attraction;
    private static VisitedLocation visitedLocation;
    private static Map<Double, Attraction> attractionList;

    @BeforeAll
    static void beforeAll() {
        user = new User(uuid1, "userName", "phoneNumber", "emailAddress");
        attraction = new Attraction("attractionName", "city", "state", 2d, 2d);
        visitedLocation = new VisitedLocation(UUID.randomUUID(), new Location(2d, 2d), Date.from(Instant.now()));
        attractionList = new HashMap<>();
        attractionList.put(2d, attraction);
    }


    // GET ATTRACTION INFORMATION TESTS //

    @Test
    void getExistingAttractionInformationTest() throws Exception {
        when(locationService.getAttraction(anyString())).thenReturn(attraction);
        mockMvc.perform(get("/attractions?attractionName=" + attraction.getAttractionName()))
                .andExpect(status().isOk());
    }

    @Test
    void getNonExistentAttractionInformationTest() throws Exception {
        when(locationService.getAttraction(anyString())).thenThrow(ElementNotFoundException.class);
        mockMvc.perform(get("/attractions?attractionName=" + attraction.getAttractionName()))
                .andExpect(status().isNotFound());
    }

    // GET ATTRACTION PROPOSALS TESTS //

    @Test
    void getAttractionProposalsWithExistingUser() throws Exception {
        when(userService.getUser(anyString())).thenReturn(user);
        when(userService.getUserCurrentLocation(anyString())).thenReturn(visitedLocation);
        when(locationService.getFiveClosestAttractionsWithDistance(any(Location.class))).thenReturn(attractionList);
        when(rewardsService.getAttractionRewardPoints(any(Attraction.class), any(User.class))).thenReturn(3);
        mockMvc.perform(get("/attractions/closest-five?username=" + user.getUsername()))
                .andExpect(status().isOk());
    }


    @Test
    void getAttractionProposalsWithNonExistentgUser() throws Exception {
        when(userService.getUser(anyString())).thenThrow(ElementNotFoundException.class);
        mockMvc.perform(get("/attractions/closest-five?username=" + user.getUsername()))
                .andExpect(status().isNotFound());
    }
}
