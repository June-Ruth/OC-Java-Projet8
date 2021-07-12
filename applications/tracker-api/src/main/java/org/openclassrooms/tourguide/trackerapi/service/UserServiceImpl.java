package org.openclassrooms.tourguide.trackerapi.service;

import org.openclassrooms.tourguide.models.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final WebClient webClientUserApi;

    public UserServiceImpl(@Qualifier("getWebClientUserApi") final WebClient webClientUserApi1) {
        webClientUserApi = webClientUserApi1;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<User> getAllUsers() {
        LOGGER.info("Getting all users");
        //TODO : IT
         return webClientUserApi
                .get()
                .uri("/users")
                .retrieve()
                .bodyToFlux(User.class)
                .collectList()
                .block();
    }
}
