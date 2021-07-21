package org.openclassrooms.tourguide.webapp;

import org.junit.jupiter.api.Test;
import org.openclassrooms.tourguide.webapp.controller.AdminController;
import org.openclassrooms.tourguide.webapp.controller.ProfileController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class WebAppTest {

    @Autowired
    private ProfileController profileController;

    @Autowired
    private AdminController adminController;

    @Test
    void contextLoads() {
        assertNotNull(profileController);
        assertNotNull(adminController);
    }
}
