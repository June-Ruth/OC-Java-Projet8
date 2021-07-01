package org.openclassrooms.tourguide.webapp.service;

import org.openclassrooms.tourguide.models.model.trip.Provider;
import org.openclassrooms.tourguide.models.model.user.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TripsServiceImpl implements TripsService {

    /**
     * @inheritDoc
     */
    @Override
    public List<Provider> getTripDeals(User user) {
        List<Provider> providers = new ArrayList<>();
        //TODO : WebClient Trip API -> TripController -> getTripDeals -> GET : "/trips/{username}
        user.setTripDeals(providers);
        return null;
    }
}
