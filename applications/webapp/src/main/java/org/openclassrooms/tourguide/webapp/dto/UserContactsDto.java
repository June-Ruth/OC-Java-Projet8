package org.openclassrooms.tourguide.webapp.dto;


import java.util.UUID;

public class UserContactsDto {

    private UUID userId;

    private String userName;

    private String phoneNumber;

    private String emailAddress;

    public UserContactsDto() { }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(final UUID userId1) {
        userId = userId1;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName1) {
        userName = userName1;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber1) {
        phoneNumber = phoneNumber1;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(final String emailAddress1) {
        emailAddress = emailAddress1;
    }
}
