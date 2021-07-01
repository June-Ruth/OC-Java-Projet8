package org.openclassrooms.tourguide.webapp.service;

import org.openclassrooms.tourguide.models.model.location.Attraction;
import org.springframework.stereotype.Service;

@Service
public class RewardsServiceImpl implements RewardsService {

    /**
     * @inheritDoc
     */
    @Override
    public int getAttractionRewardPoints(Attraction attraction) {
        //TODO
        return 0;
    }

   /* @Override
    public int getAttractionRewardPoints(Attraction attraction) {
        return 0;
    }*/
}
