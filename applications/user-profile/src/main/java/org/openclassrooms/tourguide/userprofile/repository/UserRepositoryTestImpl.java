package org.openclassrooms.tourguide.userprofile.repository;

import org.openclassrooms.tourguide.models.model.User;
import org.openclassrooms.tourguide.userprofile.helper.InternalTestInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Profile("test")
@Repository
public class UserRepositoryTestImpl implements UserRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryTestImpl.class);

    private final Map<String, User> internalUserMap;

    public UserRepositoryTestImpl() {
        LOGGER.info("TestMode enabled");
        LOGGER.debug("Initializing users");
        internalUserMap = new InternalTestInitializer().initializeInternalUsers();
        LOGGER.debug("Finished initializing users");
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(internalUserMap.values());
    }

    @Override
    public Optional<User> findByUsername(String username) {
        User user = internalUserMap.get(username);
        return Optional.of(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return internalUserMap.containsKey(username);
    }

    @Override
    public User save(User user) {
        return internalUserMap.containsKey(user.getUsername()) ?
                internalUserMap.replace(user.getUsername(), user) :
                internalUserMap.put(user.getUsername(), user);
    }
}
