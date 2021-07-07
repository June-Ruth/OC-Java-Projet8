package org.openclassrooms.tourguide.webapp.util;

import org.openclassrooms.tourguide.models.model.location.Attraction;
import org.openclassrooms.tourguide.models.model.location.Location;
import org.openclassrooms.tourguide.models.model.user.User;
import org.openclassrooms.tourguide.webapp.dto.NearAttractionDto;
import org.openclassrooms.tourguide.webapp.dto.UserContactsDto;

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
                                                                         final int rewardPoints,
                                                                         final double userDistanceFromAttraction) {
        NearAttractionDto nearAttractionDTO = new NearAttractionDto();
        nearAttractionDTO.setAttractionName(attraction.getAttractionName());
        nearAttractionDTO.setAttractionLatitude(attraction.getLatitude());
        nearAttractionDTO.setAttractionLongitude(attraction.getLongitude());
        nearAttractionDTO.setUserLatitude(userCurrentLocation.getLatitude());
        nearAttractionDTO.setUserLongitude(userCurrentLocation.getLongitude());
        nearAttractionDTO.setAttractionRewardPoints(rewardPoints);
        nearAttractionDTO.setUserDistanceFromLocation(userDistanceFromAttraction);
        return nearAttractionDTO;
    }
}
