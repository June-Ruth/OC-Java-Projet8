package org.openclassrooms.tourguide.userprofile.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openclassrooms.tourguide.models.model.User;
import org.openclassrooms.tourguide.userprofile.helper.InternalTestHelper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
public class UserRepositoryTest {

    private UserRepository userRepository;

    private static User user;
    private static final UUID uuid1 = UUID.randomUUID();
    private Map<String, User> internalUserMap;

    @BeforeEach
    void beforeEach() {
        InternalTestHelper.setInternalUserNumber(99);
        userRepository = new UserRepositoryTestImpl();
        user = new User(uuid1, "userName", "phoneNumber", "emailAddress");
    }

    // FIND USER BY USERNAME TEST //

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

    // SAVE USER TEST //

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
