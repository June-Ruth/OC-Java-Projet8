package org.openclassrooms.tourguide.webapp.controller;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
@WebMvcTest(TripDealController.class)
public class TripDealControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /*
    @MockBean
    private Service service
     */

    @BeforeAll
    static void beforeAll() {

    }
}
