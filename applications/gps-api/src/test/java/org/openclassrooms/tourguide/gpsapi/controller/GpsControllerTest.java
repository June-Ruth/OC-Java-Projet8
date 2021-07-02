package org.openclassrooms.tourguide.gpsapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openclassrooms.tourguide.gpsapi.exception.ElementNotFoundException;
import org.openclassrooms.tourguide.gpsapi.service.GpsService;
import org.openclassrooms.tourguide.models.model.location.Attraction;
import org.openclassrooms.tourguide.models.model.location.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(GpsController.class)
public class GpsControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GpsService gpsService;

    private static Attraction attraction;
    private static Map<Double, Attraction> attractionList = new HashMap<Double, Attraction>();
    private static Location userCurrentLocation;

    @BeforeAll
    static void beforeAll() {
        attraction = new Attraction("name", "city", "state", 1d, 1d);
        userCurrentLocation = new Location(2d, 2d);
        attractionList.put(2d, attraction);
    }

    // GET ATTRACTION INFORMATION TESTS //

    @Test
    void getAttractionWithExistingNameTest() throws Exception {
        when(gpsService.getAttraction(anyString())).thenReturn(attraction);
        mockMvc.perform(get("/attractions/{attractionName}", attraction.getAttractionName()))
                .andExpect(status().isOk());
    }

    @Test
    void getAttractionWithNonExistentNameTest() throws Exception {
        when(gpsService.getAttraction(anyString())).thenThrow(ElementNotFoundException.class);
        mockMvc.perform(get("/attractions/{attractionName}", attraction.getAttractionName()))
                .andExpect(status().isNotFound());
    }

    // GET NEAR BY ATTRACTIONS TESTS //

    @Test
    void getNearByAttractions() throws Exception {
        when(gpsService.getFiveNearestAttractions(any(Location.class))).thenReturn(attractionList);
        mockMvc.perform(get("/attractions/closest-five")
                .content(new ObjectMapper().writeValueAsString(userCurrentLocation))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
