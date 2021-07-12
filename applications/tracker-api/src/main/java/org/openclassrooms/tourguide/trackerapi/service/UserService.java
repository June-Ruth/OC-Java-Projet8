package org.openclassrooms.tourguide.trackerapi.service;

import org.openclassrooms.tourguide.models.model.user.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
}
