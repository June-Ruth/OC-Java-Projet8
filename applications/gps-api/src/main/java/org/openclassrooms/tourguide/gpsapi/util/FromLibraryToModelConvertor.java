package org.openclassrooms.tourguide.gpsapi.util;

import org.openclassrooms.tourguide.models.model.location.Attraction;

public class FromLibraryToModelConvertor {

    public static Attraction convertAttraction(gpsUtil.location.Attraction libraryAttraction) {
        return new Attraction(libraryAttraction.attractionName,
                libraryAttraction.city,
                libraryAttraction.city,
                libraryAttraction.latitude,
                libraryAttraction.longitude);
    }
}
