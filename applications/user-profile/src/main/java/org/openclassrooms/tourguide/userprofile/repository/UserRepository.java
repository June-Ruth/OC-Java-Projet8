package org.openclassrooms.tourguide.userprofile.repository;

import org.openclassrooms.tourguide.models.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    Optional<User> findUserById(UUID userID);

    User saveUser(User user);
}
