package org.openclassrooms.tourguide.userprofile.util;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import org.openclassrooms.tourguide.userprofile.dto.NearAttractionDto;
import org.openclassrooms.tourguide.userprofile.dto.UserContactsDto;
import org.openclassrooms.tourguide.models.model.User;

public class DtoConverter {

    private DtoConverter() {}

    public static UserContactsDto convertUserToUserContactsDto(final User user) {
        UserContactsDto userContactsDTO = new UserContactsDto();
        userContactsDTO.setUserId(user.getUserId());
        userContactsDTO.setUserName(user.getUsername());
        userContactsDTO.setPhoneNumber(user.getPhoneNumber());
        userContactsDTO.setEmailAddress(user.getEmailAddress());
        return userContactsDTO;
    }

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
