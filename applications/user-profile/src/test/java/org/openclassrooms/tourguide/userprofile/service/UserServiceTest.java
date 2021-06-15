package org.openclassrooms.tourguide.userprofile.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.openclassrooms.tourguide.models.model.User;
import org.openclassrooms.tourguide.models.model.UserPreferences;
import org.openclassrooms.tourguide.userprofile.exception.ElementNotFoundException;
import org.openclassrooms.tourguide.userprofile.repository.UserRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @Mock
    private static UserRepository userRepository;

    private static UserService userService;

    private static User user;
    private static UserPreferences userPreferences;
    private static final UUID uuid1 = UUID.randomUUID();

    @BeforeEach
    private void beforeEach() {
        userService = new UserServiceImpl(userRepository);
        user = new User(uuid1, "userName", "phoneNumber", "emailAddress");
        userPreferences = new UserPreferences();
    }

    // GET USER BY ID TESTS //

    @Test
    void getUserWithExistingIdTest() {
        when(userRepository.findUserById(any(UUID.class))).thenReturn(Optional.of(user));
        userService.getUser(uuid1);
        verify(userRepository, times(1)).findUserById(uuid1);
    }

    @Test
    void getUserWithNonExistentIdTest() {
        when(userRepository.findUserById(any(UUID.class))).thenReturn(Optional.empty());
        assertThrows(ElementNotFoundException.class, () -> userService.getUser(uuid1));
    }

    // UPDATE USER //
    @Test
    void updateExistingUserTest() {
        when(userRepository.findUserById(any(UUID.class))).thenReturn(Optional.of(user));
        when(userRepository.saveUser(any(User.class))).thenReturn(user);
        userService.updateUser(user);
        verify(userRepository, times(1)).saveUser(user);
    }

    @Test
    void updateNonExistentUserTest() {
        when(userRepository.findUserById(any(UUID.class))).thenReturn(Optional.empty());
        assertThrows(ElementNotFoundException.class, () -> userService.updateUser(user));
    }


    // GET USER PREFERENCE //

    @Test
    void getExisitingUserPreferencesTest() {
        when(userRepository.findUserById(any(UUID.class))).thenReturn(Optional.of(user));
        userService.getUserPreferences(uuid1);
        verify(userRepository, times(1)).findUserById(uuid1);
    }

    @Test
    void getNonExisitentUserPreferencesTest() {
        when(userRepository.findUserById(any(UUID.class))).thenReturn(Optional.empty());
        assertThrows(ElementNotFoundException.class, () -> userService.getUserPreferences(uuid1));
    }
}
