package org.openclassrooms.tourguide.userapi.repository;

import org.openclassrooms.tourguide.models.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    /**
     * Find all users registered in the app.
     * @return list of all user
     */
    List<User> findAll();

    /**
     * Find a user by its username.
     * @param username of the searched user
     * @return an optional of user
     */
    Optional<User> findByUsername(String username);

    /**
     * Check if an user with its username is existing.
     * @param username searched for
     * @return true if a user corresponds to the username
     */
    boolean existsByUsername(String username);

    /**
     * Save a new user or update an existing one.
     * Existence is checking by username.
     * @param user to add or update
     * @return user concerned
     */
    User save(User user);

}
