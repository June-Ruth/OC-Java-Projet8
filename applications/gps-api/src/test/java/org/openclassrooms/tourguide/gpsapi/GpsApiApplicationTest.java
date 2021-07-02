package org.openclassrooms.tourguide.gpsapi;

import org.junit.jupiter.api.Test;
import org.openclassrooms.tourguide.gpsapi.controller.GpsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@SpringBootTest
public class GpsApiApplicationTest {

    @Autowired
    private GpsController gpsController;

    @Test
    void contextLoads() {
        assertNotNull(gpsController);
    }
}
