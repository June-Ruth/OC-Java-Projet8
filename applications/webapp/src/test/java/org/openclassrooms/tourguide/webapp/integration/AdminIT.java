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

@Disabled
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class AdminIT {

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
    void getAllUsersCurrentLocationsIT() throws Exception {
        mockMvc.perform(get("/users/all-current-locations"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(handler().methodName("getAllUsersCurrentLocations"));
    }

    @Test
    void getUserLocationIT() throws Exception {
        String username = "username";
        // TODO voir pour correspondre avec l'initializer

        mockMvc.perform(get("/users/current-location?username="))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(handler().methodName("getUserLocation"))
                .andExpect(content().string(containsString(username)));
    }
}
