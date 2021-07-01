package org.openclassrooms.tourguide.webapp.service;

import org.openclassrooms.tourguide.models.model.trip.Provider;
import org.openclassrooms.tourguide.models.model.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripsServiceImpl implements TripsService {

    /**
     * @inheritDoc
     */
    @Override
    public List<Provider> getTripDeals(User user) {
        //TODO : see tourGuide.getTripDeals
        return null;
    }
}
