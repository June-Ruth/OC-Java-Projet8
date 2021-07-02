package org.openclassrooms.tourguide.rewardapi;

import org.junit.jupiter.api.Test;
import org.openclassrooms.tourguide.rewardapi.controller.RewardController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RewardApiApplicationTest {

    @Autowired
    RewardController rewardController;

    @Test
    void contextLoad() {
        assertNotNull(rewardController);
    }
}
