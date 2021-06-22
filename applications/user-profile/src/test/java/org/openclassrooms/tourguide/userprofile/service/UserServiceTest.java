package org.openclassrooms.tourguide.userprofile.service;

import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.openclassrooms.tourguide.models.model.User;
import org.openclassrooms.tourguide.userprofile.exception.ElementAlreadyExistingException;
import org.openclassrooms.tourguide.userprofile.exception.ElementNotFoundException;
import org.openclassrooms.tourguide.userprofile.repository.UserRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @Mock
    private static UserRepository userRepository;

    private static UserService userService;

    private static User user;
    private static final UUID uuid1 = UUID.randomUUID();
    private static List<User> allUsers = new ArrayList<>();

    @BeforeEach
    private void beforeEach() {
        userService = new UserServiceImpl(userRepository);
        user = new User(uuid1, "userName", "phoneNumber", "emailAddress");
        allUsers.add(user);
    }

    // GET ALL USERS TESTS //

    @Test
    void getAllUsersTest() {
        when(userRepository.findAll()).thenReturn(allUsers);
        userService.getAllUsers();
        verify(userRepository, times(1)).findAll();
    }

    // GET USER BY ID TESTS //

    @Test
    void getUserWithExistingIdTest() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        userService.getUser(user.getUsername());
        verify(userRepository, times(1)).findByUsername(user.getUsername());
    }

    @Test
    void getUserWithNonExistentIdTest() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        assertThrows(ElementNotFoundException.class, () -> userService.getUser(user.getUsername()));
        verify(userRepository, times(1)).findByUsername(user.getUsername());
    }

    // ADD USER TESTS //

    @Test
    void addNonExistentUserTest() {
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);
        userService.addUser(user);
        verify(userRepository, times(1)).existsByUsername(user.getUsername());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void addAlreadyExistingUserTest() {
        when(userRepository.existsByUsername(anyString())).thenReturn(true);
        assertThrows(ElementAlreadyExistingException.class, () -> userService.addUser(user));
        verify(userRepository, times(1)).existsByUsername(user.getUsername());
    }

    // UPDATE USER TESTS //
    @Test
    void updateExistingUserTest() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        userService.updateUser(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void updateNonExistentUserTest() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        assertThrows(ElementNotFoundException.class, () -> userService.updateUser(user));
    }


    // GET USER PREFERENCE TESTS //

    @Test
    void getExistingUserPreferencesTest() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        userService.getUserPreferences(user.getUsername());
        verify(userRepository, times(1)).findByUsername(user.getUsername());
    }

    @Test
    void getNonExistentUserPreferencesTest() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        assertThrows(ElementNotFoundException.class, () -> userService.getUserPreferences(user.getUsername()));
        verify(userRepository, times(1)).findByUsername(user.getUsername());
    }

    // GET USER REWARDS TESTS //

    @Test
    void getExistingUserRewardsTest() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        userService.getUserRewards(user.getUsername());
        verify(userRepository, times(1)).findByUsername(user.getUsername());
    }

    @Test
    void getNonExistentUserRewardsTest() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        assertThrows(ElementNotFoundException.class, () -> userService.getUserRewards(user.getUsername()));
        verify(userRepository, times(1)).findByUsername(user.getUsername());
    }

    // GET USER CURRENT LOCATION TESTS //

    @Test
    void getExistingUserCurrentLocationWithEmptyLocationTest() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        userService.getUserCurrentLocation(user.getUsername());
        verify(userRepository, times(1)).findByUsername(user.getUsername());
    }

    @Test
    void getExistingUserCurrentLocationWithNotEmptyLocationTest() {
        List<VisitedLocation> visitedLocations = new ArrayList<>();
        visitedLocations.add(new VisitedLocation(user.getUserId(), new Location(2d, 2d), Date.from(Instant.now())));
        user.setVisitedLocations(visitedLocations);
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        userService.getUserCurrentLocation(user.getUsername());
        verify(userRepository, times(1)).findByUsername(user.getUsername());
    }

    @Test
    void getNonExistentUserCurrentLocationTest() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        assertThrows(ElementNotFoundException.class, () -> userService.getUserCurrentLocation(user.getUsername()));
        verify(userRepository, times(1)).findByUsername(user.getUsername());
    }

    // GET ALL USER CURRENT LOCATIONS TESTS //

    @Test
    void getAllUserCurrentLocations() {
        when(userRepository.findAll()).thenReturn(allUsers);
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        userService.getAllUserCurrentLocations();
        verify(userRepository, times(1)).findAll();
    }
}
