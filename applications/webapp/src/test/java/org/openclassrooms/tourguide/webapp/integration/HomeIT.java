package org.openclassrooms.tourguide.webapp.integration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Locale;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class HomeIT {

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void beforeAll() {
        Locale.setDefault(Locale.US);
    }

    @Test
    void homeIT() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().isOk());
    }
}
