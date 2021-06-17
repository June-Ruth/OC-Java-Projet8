package org.openclassrooms.tourguide.userprofile.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.openclassrooms.tourguide.models.model.User;
import org.openclassrooms.tourguide.userprofile.exception.ElementNotFoundException;
import org.openclassrooms.tourguide.userprofile.repository.UserRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

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

    @BeforeEach
    private void beforeEach() {
        userService = new UserServiceImpl(userRepository);
        user = new User(uuid1, "userName", "phoneNumber", "emailAddress");
    }

    // GET USER BY ID TESTS //

    @Test
    void getUserWithExistingIdTest() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        userService.getUser(user.getUserName());
        verify(userRepository, times(1)).findByUsername(user.getUserName());
    }

    @Test
    void getUserWithNonExistentIdTest() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        assertThrows(ElementNotFoundException.class, () -> userService.getUser(user.getUserName()));
    }

    // UPDATE USER //
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


    // GET USER PREFERENCE //

    @Test
    void getExisitingUserPreferencesTest() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        userService.getUserPreferences(user.getUserName());
        verify(userRepository, times(1)).findByUsername(user.getUserName());
    }

    @Test
    void getNonExisitentUserPreferencesTest() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        assertThrows(ElementNotFoundException.class, () -> userService.getUserPreferences(user.getUserName()));
    }
}
