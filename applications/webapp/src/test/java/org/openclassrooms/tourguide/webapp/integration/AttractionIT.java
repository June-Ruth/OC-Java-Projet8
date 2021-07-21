package org.openclassrooms.tourguide.webapp.integration;

import org.junit.jupiter.api.BeforeAll;
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

@Disabled
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class AttractionIT {

    /* Before running IT, make sure that web clients are running and that internal helper is set up to 1.*/

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void beforeAll() {
        Locale.setDefault(Locale.US);
    }

    // GET ATTRACTION INFORMATION IT //

    @Test
    void getExistingAttractionInformationIT() throws Exception {
        String attractionName = "Disneyland"; //depending on GpsUtil

        mockMvc.perform(get("/attractions?attractionName=" + attractionName))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(handler().methodName("getAttractionInformation"))
                .andExpect(content().string(containsString(attractionName)));
    }

    @Test
    void getNonExistentAttractionInformationIT() throws Exception {
        String attractionName = "non existent";

        mockMvc.perform(get("/attractions?attractionName=" + attractionName))
                .andExpect(status().isNotFound())
                .andExpect(handler().methodName("getAttractionInformation"));
    }

    // GET ATTRACTION PROPOSALS IT //

    @Test
    void getAttractionProposalsWithExistingUserIT() throws Exception {
        String username = "internalUser1"; //depending on initializer

        mockMvc.perform(get("/attractions/closest-five?username=" + username))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(handler().methodName("getAttractionProposals"));
    }

    @Test
    void getAttractionProposalsWithNonExistentUserIT() throws Exception {
        String username = "non existent";

        mockMvc.perform(get("/attractions/closest-five?username=" + username))
                .andExpect(status().isNotFound())
                .andExpect(handler().methodName("getAttractionProposals"));
    }

}
