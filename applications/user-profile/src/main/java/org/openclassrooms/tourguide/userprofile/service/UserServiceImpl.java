package org.openclassrooms.tourguide.userprofile.service;

import org.openclassrooms.tourguide.models.model.User;
import org.openclassrooms.tourguide.models.model.UserPreferences;
import org.openclassrooms.tourguide.userprofile.exception.ElementNotFoundException;
import org.openclassrooms.tourguide.userprofile.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(final UserRepository userRepository1) {
        userRepository = userRepository1;
    }

    @Override
    public User getUser(UUID userId) {
        return userRepository.findUserById(userId)
                .orElseThrow(() -> new ElementNotFoundException("No user find for id : " + userId));
    }

    @Override
    public User updateUser(User updatedUser) {
        getUser(updatedUser.getUserId());
        return userRepository.saveUser(updatedUser);
    }

    @Override
    public UserPreferences getUserPreferences(UUID userId) {
        return getUser(userId).getUserPreferences();
    }
}
