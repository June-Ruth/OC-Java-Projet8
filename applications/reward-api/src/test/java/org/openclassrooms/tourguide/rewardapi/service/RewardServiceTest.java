package org.openclassrooms.tourguide.rewardapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import rewardCentral.RewardCentral;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class RewardServiceTest {

    @Mock
    private RewardCentral rewardCentral;

    private static RewardService rewardService;

    private static UUID uuid1 = UUID.randomUUID();
    private static UUID uuid2 = UUID.randomUUID();

    @BeforeEach
    void beforeEach() {
        rewardService = new RewardServiceImpl(rewardCentral);
    }

    //GET REWARD POINTS TESTS //

    @Test
    void getRewardPointsTest() {
        when(rewardCentral.getAttractionRewardPoints(any(UUID.class), any(UUID.class))).thenReturn(2);
        rewardService.getRewardPoints(uuid1,uuid2);
        verify(rewardCentral, times(1)).getAttractionRewardPoints(uuid1, uuid2);
    }
}
