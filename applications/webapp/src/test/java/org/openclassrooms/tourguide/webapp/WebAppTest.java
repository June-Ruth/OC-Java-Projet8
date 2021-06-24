package org.openclassrooms.tourguide.webapp;

import org.junit.jupiter.api.Test;
import org.openclassrooms.tourguide.webapp.controller.AdminWebController;
import org.openclassrooms.tourguide.webapp.controller.UserWebController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class WebAppTest {

    @Autowired
    private UserWebController userWebController;

    @Autowired
    private AdminWebController adminWebController;

    @Test
    void contextLoads() {
        assertNotNull(userWebController);
        assertNotNull(adminWebController);
    }
}
