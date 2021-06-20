package org.openclassrooms.tourguide.localization.controller;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openclassrooms.tourguide.localization.exception.ElementNotFoundException;
import org.openclassrooms.tourguide.localization.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AttractionController.class)
public class AttractionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LocationService locationService;

    private static Attraction attraction;
    private static VisitedLocation visitedLocation;
    private static List<Attraction> attractionList = new ArrayList<>();

    @BeforeAll
    static void beforeAll() {
        attraction = new Attraction("name", "city", "state", 1d, 1d);
        visitedLocation = new VisitedLocation(UUID.randomUUID(), new Location(2d, 2d), Date.from(Instant.now()));
        attractionList.add(attraction);
    }

    // GET ATTRACTION INFORMATION TESTS //

    @Test
    void getAttractionWithExistingNameTest() throws Exception {
        when(locationService.getAttraction(anyString())).thenReturn(attraction);
        mockMvc.perform(get("/attractions/{attractionName}", attraction.attractionName))
                .andExpect(status().isOk());
    }

    @Test
    void getAttractionWithNonExistentNameTest() throws Exception {
        when(locationService.getAttraction(anyString())).thenThrow(ElementNotFoundException.class);
        mockMvc.perform(get("/attractions/{attractionName}", attraction.attractionName))
                .andExpect(status().isNotFound());
    }

    // GET NEAR BY ATTRACTIONS TESTS //

    @Test
    void getNearByAttractionsWithExistingUser() throws Exception {
        when(locationService.getUserCurrentLocation(anyString())).thenReturn(visitedLocation);
        when(locationService.getFiveNearestAttractions(any(VisitedLocation.class))).thenReturn(attractionList);
        when(locationService.getAttractionRewardPoints(any(Attraction.class))).thenReturn(2);
        mockMvc.perform(get("/attractions/closest-five?username=" + "UserName"))
                .andExpect(status().isOk());
    }

    @Test
    void getNearByAttractionsWithNonExistentUser() throws Exception {
        when(locationService.getUserCurrentLocation(anyString())).thenThrow(ElementNotFoundException.class);
        mockMvc.perform(get("/attractions/closest-five?username=" + "UserName"))
                .andExpect(status().isNotFound());
    }


}
