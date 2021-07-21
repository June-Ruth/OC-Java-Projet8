package org.openclassrooms.tourguide.tripapi.service;

import org.openclassrooms.tourguide.models.model.trip.Provider;

import java.util.List;
import java.util.UUID;

public interface TripService {

    /**
     * Get trip deals for a specified user.
     * @param userId
     * @param numberOfAdults
     * @param numberOfChildren
     * @param tripDuration
     * @param cumulativeRewardPoints
     * @return list of providers
     */
    List<Provider> getTripDeals(UUID userId, int numberOfAdults, int numberOfChildren, int tripDuration, int cumulativeRewardPoints);
}
