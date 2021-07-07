package org.openclassrooms.tourguide.userapi.service;

import org.openclassrooms.tourguide.models.model.location.VisitedLocation;
import org.openclassrooms.tourguide.models.model.user.User;
import org.openclassrooms.tourguide.userapi.exception.ElementAlreadyExistingException;
import org.openclassrooms.tourguide.userapi.exception.ElementNotFoundException;
import org.openclassrooms.tourguide.userapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;

    public UserServiceImpl(final UserRepository userRepository1) {
        userRepository = userRepository1;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<User> getAllUsers() {
        LOGGER.info("Getting all users.");
        return userRepository.findAll();
    }

    /**
     * @inheritDoc
     */
    @Override
    public User getUser(String username) {
        LOGGER.info("Getting user : " + username);
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ElementNotFoundException("No user find for username : " + username));
    }

    /**
     * @inheritDoc
     */
    @Override
    public User addUser(User user) {
        LOGGER.info("Adding user : " + user);
        if(userRepository.existsByUsername(user.getUsername())) {
            throw new ElementAlreadyExistingException(
                    "User " + user.getUsername() + " is already existing");
        } else {
            return userRepository.save(user);
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public User updateUser(User updatedUser) {
        LOGGER.info("Updating user : " + updatedUser);
        getUser(updatedUser.getUsername());
        return userRepository.save(updatedUser);
    }

    /**
     * @inheritDoc
     */
    @Override
    public VisitedLocation getUserCurrentLocation(final String username) {
        LOGGER.info("Getting user current location for user : " + username);
        List<VisitedLocation> userVisitedLocations = getUser(username).getVisitedLocations();
        if (userVisitedLocations.isEmpty()) {
            return null;
        } else {
            return userVisitedLocations.get(userVisitedLocations.size() - 1);
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<VisitedLocation> getAllUserCurrentLocations() {
        LOGGER.info("Getting all users current locations.");
        List<User> allUsers = getAllUsers();
        List<VisitedLocation> allCurrentLocations = new ArrayList<>();
        for(User user : allUsers) {
            VisitedLocation currentLocation = getUserCurrentLocation(user.getUsername());
            allCurrentLocations.add(currentLocation);
        }
        return allCurrentLocations;
    }
}
