package org.openclassrooms.tourguide.tripapi;

import org.junit.jupiter.api.Test;
import org.openclassrooms.tourguide.tripapi.controller.TripController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@SpringBootTest
public class TripApiApplicationTest {

    @Autowired
    private TripController tripController;

    @Test
    void contextLoads() {
        assertNotNull(tripController);
    }

}
