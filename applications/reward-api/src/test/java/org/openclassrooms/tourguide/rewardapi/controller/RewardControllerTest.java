package org.openclassrooms.tourguide.rewardapi.controller;

import org.junit.jupiter.api.Test;
import org.openclassrooms.tourguide.rewardapi.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(RewardController.class)
public class RewardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RewardService rewardService;

    // GET REWARD POINTS TESTS //

    @Test
    void getRewardPointsTest() throws Exception {
        when(rewardService.getRewardPoints(any(UUID.class), any(UUID.class))).thenReturn(2);
        mockMvc.perform(get("/rewards?attractionId=" + UUID.randomUUID() + "&userId=" + UUID.randomUUID()))
                .andExpect(status().isOk());
    }

}
