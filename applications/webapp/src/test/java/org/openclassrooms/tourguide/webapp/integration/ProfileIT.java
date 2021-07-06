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

@Disabled
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class ProfileIT {

    //TODO : voir comment param les différents bean notamment en param internal test helper

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

    @Test
    void getUserProfileIT() throws Exception {
        String username = "username";
        // TODO voir pour correspondre avec l'initializer
        mockMvc.perform(get("/profile?username=" + username))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(handler().methodName("getUserProfile"));
    }

    @Test
    void getUserPreferencesIT() throws Exception {
        String username = "username";
        // TODO voir pour correspondre avec l'initializer
        mockMvc.perform(get("/profile/preferences?username=" + username))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(handler().methodName("getUserPreferences"));
    }

    @Test
    void updateUserPreferencesIT() throws Exception {
        String username = "username";
        // TODO voir pour correspondre avec l'initializer
        mockMvc.perform(put("/profile/preferences?username=" + username)
                .content(new ObjectMapper().writeValueAsString(userPreferences))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(handler().methodName("updateUserPreferences"));
    }

    @Test
    void getUserRewardsIT() throws Exception {
        String username = "username";
        // TODO voir pour correspondre avec l'initializer
        mockMvc.perform(get("/profile/rewards?username=" + username))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(handler().methodName("getUserRewards"));
    }
}
