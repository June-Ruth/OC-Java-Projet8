package org.openclassrooms.tourguide.userprofile.service;

import org.openclassrooms.tourguide.models.model.User;
import org.openclassrooms.tourguide.models.model.UserPreferences;
import org.openclassrooms.tourguide.userprofile.exception.ElementNotFoundException;
import org.openclassrooms.tourguide.userprofile.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(final UserRepository userRepository1) {
        userRepository = userRepository1;
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ElementNotFoundException("No user find for username : " + username));
    }

    @Override
    public User updateUser(User updatedUser) {
        getUser(updatedUser.getUserName());
        return userRepository.save(updatedUser);
    }

    @Override
    public UserPreferences getUserPreferences(String username) {
        return getUser(username).getUserPreferences();
    }
}
