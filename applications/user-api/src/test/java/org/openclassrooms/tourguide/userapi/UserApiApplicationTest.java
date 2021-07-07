package org.openclassrooms.tourguide.userapi;

import org.junit.jupiter.api.Test;
import org.openclassrooms.tourguide.userapi.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@SpringBootTest
public class UserApiApplicationTest {

    @Autowired
    private UserController userController;

    @Test
    void contextLoads() {
        assertNotNull(userController);
    }
}
