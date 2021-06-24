package org.openclassrooms.tourguide.userprofile.service;


import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.openclassrooms.tourguide.models.model.User;
import org.openclassrooms.tourguide.models.model.UserPreferences;
import org.openclassrooms.tourguide.models.model.UserReward;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUser(String username);

    User addUser(User user);

    User updateUser(User updatedUser);

    VisitedLocation getUserCurrentLocation(String username);

    List<VisitedLocation> getAllUserCurrentLocations();
}
