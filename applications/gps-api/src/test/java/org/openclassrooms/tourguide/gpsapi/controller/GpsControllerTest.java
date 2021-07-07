package org.openclassrooms.tourguide.gpsapi.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openclassrooms.tourguide.gpsapi.exception.ElementNotFoundException;
import org.openclassrooms.tourguide.gpsapi.service.GpsService;
import org.openclassrooms.tourguide.models.model.location.Attraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

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
    private static List<Attraction> attractionList = new ArrayList<>();

    @BeforeAll
    static void beforeAll() {
        attraction = new Attraction("name", "city", "state", 1d, 1d);
        attractionList.add(attraction);
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

    // GET ALL ATTRACTIONS //
    @Test
    void getAllAttractionsTest() throws Exception {
        when(gpsService.getAllAttractions()).thenReturn(attractionList);
        mockMvc.perform(get("/attractions"))
                .andExpect(status().isOk());
    }
}
