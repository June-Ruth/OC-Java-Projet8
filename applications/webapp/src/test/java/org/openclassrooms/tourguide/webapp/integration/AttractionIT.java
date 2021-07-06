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

@Disabled
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class AttractionIT {

    //TODO : voir comment param les différents bean notamment en param internal test helper

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void beforeAll() {
        Locale.setDefault(Locale.US);
    }

    @BeforeEach
    public void beforeEach() {
    }

    @Test
    void getAttractionInformationIT() throws Exception {
        String attractionName = "username";
        // TODO voir pour correspondre avec l'existant'

        mockMvc.perform(get("/attractions?attractionName="))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(handler().methodName("getAttractionInformation"))
                .andExpect(content().string(containsString(attractionName)));
    }

    @Test
    void getAttractionProposalsIT() throws Exception {
        String username = "username";
        // TODO voir pour correspondre avec l'initializer

        mockMvc.perform(get("/attractions/closest-five?username="))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(handler().methodName("getAttractionProposals"));
    }

}
