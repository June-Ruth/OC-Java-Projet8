package org.openclassrooms.tourguide.userapi.repository;

import org.openclassrooms.tourguide.models.model.user.User;
import org.openclassrooms.tourguide.userapi.helper.InternalTestInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//@Profile("test")
@Repository
public class UserRepositoryTestImpl implements UserRepository {

    /**
     * @see Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryTestImpl.class);

    /**
     * Internal map used for testing, as database that contains all users.
     */
    private final Map<String, User> internalUserMap;

    /**
     * Constructor for test implementation.
     * Use the internal test initializer to create a map which manage user as a database.
     * @see InternalTestInitializer
     */
    public UserRepositoryTestImpl() {
        LOGGER.info("TestMode enabled");
        LOGGER.debug("Initializing users");
        internalUserMap = new InternalTestInitializer().initializeInternalUsers();
        LOGGER.debug("Finished initializing users");
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<User> findAll() {
        LOGGER.info("Finding all users.");
        return new ArrayList<>(internalUserMap.values());
    }

    /**
     * @inheritDoc
     */
    @Override
    public Optional<User> findByUsername(String username) {
        LOGGER.info("Finding user : " + username);
        User user = internalUserMap.get(username);
        return Optional.of(user);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean existsByUsername(String username) {
        LOGGER.info("Checking if user : " + username + " exists.");
        return internalUserMap.containsKey(username);
    }

    /**
     * @inheritDoc
     */
    @Override
    public User save(User user) {
        LOGGER.info("Saving user : " + user);
        return internalUserMap.containsKey(user.getUsername()) ?
                internalUserMap.replace(user.getUsername(), user) :
                internalUserMap.put(user.getUsername(), user);
    }
}
