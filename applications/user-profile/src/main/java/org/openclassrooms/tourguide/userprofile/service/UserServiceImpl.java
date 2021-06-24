package org.openclassrooms.tourguide.userprofile.service;

import gpsUtil.location.VisitedLocation;
import org.openclassrooms.tourguide.models.model.User;
import org.openclassrooms.tourguide.userprofile.exception.ElementAlreadyExistingException;
import org.openclassrooms.tourguide.userprofile.exception.ElementNotFoundException;
import org.openclassrooms.tourguide.userprofile.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    //private WebClient webClientAttraction;

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
    public VisitedLocation getUserCurrentLocation(final String username) {
        List<VisitedLocation> userVisitedLocations = getUser(username).getVisitedLocations();
        if (userVisitedLocations.isEmpty()) {
            //TODO : if VisitedLocations is empty, then track location
            return null;
        } else {
            return userVisitedLocations.get(userVisitedLocations.size() - 1);
        }
    }

    @Override
    public List<VisitedLocation> getAllUserCurrentLocations() {
        List<User> allUsers = getAllUsers();
        List<VisitedLocation> allCurrentLocations = new ArrayList<>();
        for(User user : allUsers) {
            VisitedLocation currentLocation = getUserCurrentLocation(user.getUsername());
            allCurrentLocations.add(currentLocation);
        }
        return allCurrentLocations;
    }
}
