package org.openclassrooms.tourguide.trackerapi.service;

import org.openclassrooms.tourguide.models.model.location.VisitedLocation;
import org.openclassrooms.tourguide.models.model.user.User;
import org.openclassrooms.tourguide.models.model.user.UserReward;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    VisitedLocation addToVisitedLocationsOfUser(VisitedLocation visitedLocation, User user);

    void addUserRewardToUser(UserReward newReward, User user);
}
