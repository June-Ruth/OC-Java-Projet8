package org.openclassrooms.tourguide.localization.controller;

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

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LocationController.class)
public class LocationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LocationService locationService;

    private static VisitedLocation visitedLocation;
    private static List<VisitedLocation> visitedLocationList;

    @BeforeAll
    static void beforeAll() {
        visitedLocation = new VisitedLocation(UUID.randomUUID(), new Location(2d, 2d), Date.from(Instant.now()));
        visitedLocationList = new ArrayList<>();
        visitedLocationList.add(visitedLocation);
    }

    // GET ALL CURRENT LOCATIONS TESTS //
    @Test
    void getAllCurrentLocations() throws Exception  {
        when(locationService.getAllUserCurrentLocations()).thenReturn(visitedLocationList);
        mockMvc.perform(get("/location/users"))
                .andExpect(status().isOk());
    }


    // GET USER LOCATION TESTS //

    @Test
    void getExistingUserLocation() throws Exception {
        when(locationService.getUserCurrentLocation(anyString())).thenReturn(visitedLocation);
        mockMvc.perform(get("/location?username=" + "UserName"))
                .andExpect(status().isOk());
    }

    @Test
    void getNonExistentUserLocation() throws Exception {
        when(locationService.getUserCurrentLocation(anyString())).thenThrow(ElementNotFoundException.class);
        mockMvc.perform(get("/location?username=" + "UserName"))
                .andExpect(status().isNotFound());
    }
}
