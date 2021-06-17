package org.openclassrooms.tourguide.userprofile.service;

import org.openclassrooms.tourguide.models.model.User;
import org.openclassrooms.tourguide.models.model.UserPreferences;
import org.openclassrooms.tourguide.models.model.UserReward;
import org.openclassrooms.tourguide.userprofile.exception.ElementAlreadyExistingException;
import org.openclassrooms.tourguide.userprofile.exception.ElementNotFoundException;
import org.openclassrooms.tourguide.userprofile.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(final UserRepository userRepository1) {
        userRepository = userRepository1;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ElementNotFoundException("No user find for username : " + username));
    }

    @Override
    public User addUser(User user) {
        if(userRepository.existsByUsername(user.getUsername())) {
            throw new ElementAlreadyExistingException(
                    "User " + user.getUsername() + " is already existing");
        } else {
            return userRepository.save(user);
        }
    }

    @Override
    public User updateUser(User updatedUser) {
        getUser(updatedUser.getUsername());
        return userRepository.save(updatedUser);
    }

    @Override
    public UserPreferences getUserPreferences(String username) {
        return getUser(username).getUserPreferences();
    }


    //TODO : pass to rewards MS
    @Override
    public List<UserReward> getUserRewards(String username) {
        User user = getUser(username);
        return user.getUserRewards();
    }
}
