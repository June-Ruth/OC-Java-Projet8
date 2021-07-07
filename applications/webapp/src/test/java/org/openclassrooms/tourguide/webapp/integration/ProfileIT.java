package org.openclassrooms.tourguide.webapp.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openclassrooms.tourguide.models.model.user.UserPreferences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Locale;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class ProfileIT {

    /* Before running IT, make sure that web clients are running and that internal helper is set up to 1.*/

    /* NB : here, we no test what happens in case of invalid data for updating user information or preferences
    because we don't have any information about what means valid or invalid.*/

    @Autowired
    private MockMvc mockMvc;

    private UserPreferences userPreferences;

    @BeforeAll
    public static void beforeAll() {
        Locale.setDefault(Locale.US);
    }

    @BeforeEach
    public void beforeEach() {
        userPreferences = new UserPreferences();
    }

    // GET USER PROFILE IT //

    @Test
    void getUserProfileWithExistingUsernameIT() throws Exception {
        String username = "internalUser1"; //depending on initializer
        mockMvc.perform(get("/profile?username=" + username))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(handler().methodName("getUserProfile"));
    }

    @Test
    void getUserProfileWithNonExistentUsernameIT() throws Exception {
        String username = "non existent";
        mockMvc.perform(get("/profile?username=" + username))
                .andExpect(status().isNotFound())
                .andExpect(handler().methodName("getUserProfile"));
    }


    // GET USER PREFERENCES IT //

    @Test
    void getUserPreferencesWithExistingUsernameIT() throws Exception {
        String username = "internalUser1"; //depending on initializer
        mockMvc.perform(get("/profile/preferences?username=" + username))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(handler().methodName("getUserPreferences"));
    }

    @Test
    void getUserPreferencesWithNonExistentUsernameIT() throws Exception {
        String username = "non existent";
        mockMvc.perform(get("/profile/preferences?username=" + username))
                .andExpect(status().isNotFound())
                .andExpect(handler().methodName("getUserPreferences"));
    }

    // UPDATE USER PREFERENCES IT //

    @Test
    void updateUserPreferencesWithExistingUsernameAndValidDataIT() throws Exception {
        String username = "internalUser1"; //depending on initializer
        mockMvc.perform(put("/profile/preferences?username=" + username)
                .content(new ObjectMapper().writeValueAsString(userPreferences))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(handler().methodName("updateUserPreferences"));
    }

    @Test
    void updateUserPreferencesWithNonExistentUsernameAndValidDataIT() throws Exception {
        String username = "non existent";
        mockMvc.perform(put("/profile/preferences?username=" + username)
                .content(new ObjectMapper().writeValueAsString(userPreferences))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(handler().methodName("updateUserPreferences"));
    }

    // GET USER REWARDS IT //

    @Test
    void getUserRewardsWithExistingUsernameIT() throws Exception {
        String username = "internalUser1"; //depending on initializer
        mockMvc.perform(get("/profile/rewards?username=" + username))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(handler().methodName("getUserRewards"));
    }

    @Test
    void getUserRewardsWithNonExistentUsernameIT() throws Exception {
        String username = "non existent";
        mockMvc.perform(get("/profile/rewards?username=" + username))
                .andExpect(status().isNotFound())
                .andExpect(handler().methodName("getUserRewards"));
    }

}
