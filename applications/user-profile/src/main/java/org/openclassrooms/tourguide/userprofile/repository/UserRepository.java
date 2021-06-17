package org.openclassrooms.tourguide.userprofile.repository;

import org.openclassrooms.tourguide.models.model.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByUsername(String username);

    User save(User user);
}
