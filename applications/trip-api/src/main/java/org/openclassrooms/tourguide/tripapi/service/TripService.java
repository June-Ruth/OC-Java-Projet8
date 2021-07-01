package org.openclassrooms.tourguide.tripapi.service;

import org.openclassrooms.tourguide.models.model.trip.Provider;
import org.openclassrooms.tourguide.models.model.user.User;

import java.util.List;

public interface TripService {

    /**
     * Get trip deals for a specified user.
     * @param user specified
     * @return list of providers
     */
    List<Provider> getTripDeals(User user);
}
