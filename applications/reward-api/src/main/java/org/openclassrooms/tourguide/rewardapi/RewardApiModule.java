package org.openclassrooms.tourguide.rewardapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rewardCentral.RewardCentral;

@Configuration
public class RewardApiModule {

    @Bean
    public RewardCentral getRewardCentral() {
        return new RewardCentral();
    }
}
