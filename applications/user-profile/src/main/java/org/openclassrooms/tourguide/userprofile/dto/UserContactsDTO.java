package org.openclassrooms.tourguide.userprofile.dto;


import java.util.UUID;

public class UserContactsDTO {

    private UUID userId;

    private String userName;

    private String phoneNumber;

    private String emailAddress;

    public UserContactsDTO() { }

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

    @Override
    public String toString() {
        return "UserContactsDTO{"
                + "userId='" + userId + '\''
                + ", userName='" + userName + '\''
                + ", phoneNumber='" + phoneNumber + '\''
                + ", emailAddress=" + emailAddress
                + '}';
    }
}
