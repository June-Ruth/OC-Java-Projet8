package org.openclassrooms.tourguide.localization.controller;

import gpsUtil.location.Attraction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openclassrooms.tourguide.localization.exception.ElementNotFoundException;
import org.openclassrooms.tourguide.localization.service.AttractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AttractionController.class)
public class AttractionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AttractionService attractionService;

    private static Attraction attraction;

    @BeforeAll
    static void beforeAll() {
        attraction = new Attraction("name", "city", "state", 1d, 1d);
    }

    // GET ATTRACTION INFORMATION TESTS //

    @Test
    void getAttractionWithExistingNameTest() throws Exception {
        when(attractionService.getAttraction(anyString())).thenReturn(attraction);
        mockMvc.perform(get("/attractions/{attractionName}", attraction.attractionName))
                .andExpect(status().isOk());
    }

    @Test
    void getAttractionWithNonExistentNameTest() throws Exception {
        when(attractionService.getAttraction(anyString())).thenThrow(ElementNotFoundException.class);
        mockMvc.perform(get("/attractions/{attractionName}", attraction.attractionName))
                .andExpect(status().isNotFound());
    }
}
