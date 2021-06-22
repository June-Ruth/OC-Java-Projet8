package org.openclassrooms.tourguide.userprofile.util;

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
}
