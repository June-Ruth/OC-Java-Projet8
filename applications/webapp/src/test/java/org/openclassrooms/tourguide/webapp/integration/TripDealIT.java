package org.openclassrooms.tourguide.webapp.integration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Locale;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class TripDealIT {

    /* Before running IT, make sure that web clients are running and that internal helper is set up to 1.*/

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void beforeAll() {
        Locale.setDefault(Locale.US);
    }

    //GET TRIP DEALS TESTS //

    @Test
    void getTripDealsForExistingUserIT() throws Exception {
        String username = "internalUser1"; //depending on initializer
        mockMvc.perform(get("/trip-deals?username=" + username))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(handler().methodName("getTripDeals"));
    }

    @Test
    void getTripDealsForNonExistentUserIT() throws Exception {
        String username = "non existent";
        mockMvc.perform(get("/trip-deals?username=" + username))
                .andExpect(status().isNotFound())
                .andExpect(handler().methodName("getTripDeals"));
    }
}
