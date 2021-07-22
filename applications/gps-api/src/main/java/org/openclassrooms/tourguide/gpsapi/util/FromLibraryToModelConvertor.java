package org.openclassrooms.tourguide.gpsapi.util;

import org.openclassrooms.tourguide.models.model.location.Attraction;
import org.openclassrooms.tourguide.models.model.location.Location;
import org.openclassrooms.tourguide.models.model.location.VisitedLocation;

public class FromLibraryToModelConvertor {

    public static Attraction convertAttraction(final gpsUtil.location.Attraction libraryAttraction) {
        return new Attraction(libraryAttraction.attractionName,
                libraryAttraction.city,
                libraryAttraction.state,
                libraryAttraction.latitude,
                libraryAttraction.longitude);
    }

    public static VisitedLocation convertVisitedLocation(final gpsUtil.location.VisitedLocation libraryVisitedLocation) {
        return new VisitedLocation(libraryVisitedLocation.userId,
                new Location(libraryVisitedLocation.location.latitude, libraryVisitedLocation.location.longitude),
                libraryVisitedLocation.timeVisited);
    }
}
