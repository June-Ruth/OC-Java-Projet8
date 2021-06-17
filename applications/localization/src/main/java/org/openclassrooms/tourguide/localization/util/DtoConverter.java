package org.openclassrooms.tourguide.localization.util;

import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.openclassrooms.tourguide.localization.dto.NearAttractionDTO;

public class DtoConverter {

    private DtoConverter() { }

    public static NearAttractionDTO convertAttractionToNearAttractionDto(final Attraction attraction,
                                                                         final VisitedLocation lastUserLocation,
                                                                         final int rewardPoints) {
        NearAttractionDTO nearAttractionDTO = new NearAttractionDTO();
        nearAttractionDTO.setAttractionName(attraction.attractionName);
        nearAttractionDTO.setAttractionLatitude(attraction.latitude);
        nearAttractionDTO.setAttractionLongitude(attraction.longitude);
        nearAttractionDTO.setUserLatitude(lastUserLocation.location.latitude);
        nearAttractionDTO.setUserLongitude(lastUserLocation.location.longitude);
        nearAttractionDTO.setAttractionRewardPoints(rewardPoints);
        return nearAttractionDTO;
    }

}
