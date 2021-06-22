package org.openclassrooms.tourguide.attraction.util;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import org.openclassrooms.tourguide.attraction.dto.NearAttractionDto;

public class DtoConverter {

    private DtoConverter() { }

    public static NearAttractionDto convertAttractionToNearAttractionDto(final Attraction attraction,
                                                                         final Location userCurrentLocation,
                                                                         final int rewardPoints) {
        NearAttractionDto nearAttractionDTO = new NearAttractionDto();
        nearAttractionDTO.setAttractionName(attraction.attractionName);
        nearAttractionDTO.setAttractionLatitude(attraction.latitude);
        nearAttractionDTO.setAttractionLongitude(attraction.longitude);
        nearAttractionDTO.setUserLatitude(userCurrentLocation.latitude);
        nearAttractionDTO.setUserLongitude(userCurrentLocation.longitude);
        nearAttractionDTO.setAttractionRewardPoints(rewardPoints);
        return nearAttractionDTO;
    }
}
