package org.openclassrooms.tourguide.webapp.controller;

import org.junit.jupiter.api.BeforeAll;
import org.openclassrooms.tourguide.webapp.service.TourGuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
@WebMvcTest(UserWebController.class)
public class UserWebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TourGuideService tourGuideService;

    @BeforeAll
    static void beforeAll() { }

}
