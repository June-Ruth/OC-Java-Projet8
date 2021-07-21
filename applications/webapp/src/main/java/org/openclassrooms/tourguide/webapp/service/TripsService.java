package org.openclassrooms.tourguide.webapp.service;

import org.openclassrooms.tourguide.models.model.trip.Provider;
import org.openclassrooms.tourguide.models.model.user.User;

import java.util.List;

public interface TripsService {

    /**
     * Get trip deals for specified user.
     * @param user specified
     * @return list of providers
     */
    List<Provider> getTripDeals(User user);
}
