package org.openclassrooms.tourguide.userprofile.util;

import org.openclassrooms.tourguide.userprofile.dto.UserContactsDTO;
import org.openclassrooms.tourguide.models.model.User;

public class DtoConverter {

    private DtoConverter() {}

    public static UserContactsDTO convertUserToUserContactsDto(User user) {
        UserContactsDTO userContactsDTO = new UserContactsDTO();
        userContactsDTO.setUserId(user.getUserId());
        userContactsDTO.setUserName(user.getUserName());
        userContactsDTO.setPhoneNumber(user.getPhoneNumber());
        userContactsDTO.setEmailAddress(user.getEmailAddress());
        return userContactsDTO;
    }
}