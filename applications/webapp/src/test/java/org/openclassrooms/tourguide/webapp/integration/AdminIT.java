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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Disabled
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class AdminIT {

    /* Before running IT, make sure that web clients are running and that internal helper is set up to 1.*/

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void beforeAll() {
        Locale.setDefault(Locale.US);
    }

    // HOME PAGE IT //

    @Test
    void homePageTest() throws Exception {
        mockMvc.perform(get("/admin/home"))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("homePage"));
    }

    // GET ALL USERS CURRENT LOCATIONS IT //

    @Test
    void getAllUsersCurrentLocationsIT() throws Exception {
        mockMvc.perform(get("/admin/users/all-current-locations"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(handler().methodName("getAllUsersCurrentLocations"));
    }

    // GET USER LOCATION IT //

    @Test
    void getExistingUserLocationIT() throws Exception {
        String username = "internalUser1"; //depending on initializer

        mockMvc.perform(get("/admin/users/current-location?username=" + username))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(handler().methodName("getUserLocation"));
    }

    @Test
    void getNonExistentUserLocationIT() throws Exception {
        String username = "non existent";

        mockMvc.perform(get("/admin/users/current-location?username=" + username))
                .andExpect(status().isNotFound())
                .andExpect(handler().methodName("getUserLocation"));
    }
}
