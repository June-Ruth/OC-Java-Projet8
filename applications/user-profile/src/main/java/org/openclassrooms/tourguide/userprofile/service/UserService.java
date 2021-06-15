package org.openclassrooms.tourguide.userprofile.service;


import org.openclassrooms.tourguide.models.model.User;
import org.openclassrooms.tourguide.models.model.UserPreferences;

import java.util.UUID;

public interface UserService {

    User getUser(UUID userId);

    User updateUser(User updatedUser);

    UserPreferences getUserPreferences(UUID userId);

    UserPreferences updateUserPreferences(UUID userId);
}
