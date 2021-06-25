package org.openclassrooms.tourguide.userapi.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openclassrooms.tourguide.models.model.user.User;
import org.openclassrooms.tourguide.userapi.helper.InternalTestHelper;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SuppressWarnings("OptionalGetWithoutIsPresent")
@ExtendWith(SpringExtension.class)
public class UserRepositoryTest {

    private UserRepository userRepository;

    private static User user;
    private static final UUID uuid1 = UUID.randomUUID();

    @BeforeEach
    void beforeEach() {
        InternalTestHelper.setInternalUserNumber(99);
        userRepository = new UserRepositoryTestImpl();
        user = new User(uuid1, "userName", "phoneNumber", "emailAddress");
    }

    // FIND ALL TESTS //

    @Test
    void findAllTest() {
        assertEquals(99, userRepository.findAll().size());
    }

    // FIND BY USERNAME TESTS //

    @Test
    void findExistingUserByUsernameTest() {
        userRepository.save(user);
        Optional<User> retrievedUser = userRepository.findByUsername(user.getUsername());
        assertEquals(Optional.of(user), retrievedUser);
    }

    @Test
    void findNonExistentUserByUsernameTest() {
        assertThrows(NullPointerException.class, () -> userRepository.findByUsername(user.getUsername()));
    }

    // EXISTS BY USERNAME TESTS //

    @Test
    void existsByUsernameTrueTest() {
        userRepository.save(user);
        assertTrue(userRepository.existsByUsername(user.getUsername()));
    }

    @Test
    void existsByUsernameFalseTest() {
        assertFalse(userRepository.existsByUsername(user.getUsername()));
    }

    // SAVE TESTS //

    @Test
    void saveExistingUserTest() {
        userRepository.save(user);
        user.setEmailAddress("emailTest");
        userRepository.save(user);
        Optional<User> retrievedUser = userRepository.findByUsername(user.getUsername());
        assertEquals("emailTest", retrievedUser.get().getEmailAddress());
    }

    @Test
    void saveNonExistentUserTest() {
        userRepository.save(user);
        Optional<User> retrievedUser = userRepository.findByUsername(user.getUsername());
        assertEquals(Optional.of(user), retrievedUser);
    }
}
