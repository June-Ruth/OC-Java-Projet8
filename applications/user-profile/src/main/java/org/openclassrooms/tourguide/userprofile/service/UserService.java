package org.openclassrooms.tourguide.userprofile.service;


import org.openclassrooms.tourguide.models.model.User;
import org.openclassrooms.tourguide.models.model.UserPreferences;

import java.util.UUID;

public interface UserService {

    User getUser(String username);

    User updateUser(User updatedUser);

    UserPreferences getUserPreferences(String username);
}
