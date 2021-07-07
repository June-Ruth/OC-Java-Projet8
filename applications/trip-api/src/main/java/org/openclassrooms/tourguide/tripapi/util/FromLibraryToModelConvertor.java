package org.openclassrooms.tourguide.tripapi.util;

import org.openclassrooms.tourguide.models.model.trip.Provider;

public class FromLibraryToModelConvertor {

    public static Provider convertProvider(tripPricer.Provider libraryProvider) {
        return new Provider(libraryProvider.tripId, libraryProvider.name, libraryProvider.price);
    }
}
