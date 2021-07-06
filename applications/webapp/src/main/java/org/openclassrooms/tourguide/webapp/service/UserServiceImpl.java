package org.openclassrooms.tourguide.webapp.service;

import org.openclassrooms.tourguide.models.model.location.VisitedLocation;
import org.openclassrooms.tourguide.models.model.user.User;
import org.openclassrooms.tourguide.models.model.user.UserPreferences;
import org.openclassrooms.tourguide.models.model.user.UserReward;
import org.openclassrooms.tourguide.webapp.exception.ElementNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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
    public List<VisitedLocation> getAllUserCurrentLocations() {
        LOGGER.info("Getting all users current locations");
        return webClientUserApi
                .get()
                .uri("/users/all-current-locations")
                .retrieve()
                .bodyToFlux(VisitedLocation.class)
                .collectList()
                .block();
    }

    /**
     * @inheritDoc
     */
    @Override
    public VisitedLocation getUserCurrentLocation(String username) {
        LOGGER.info(("Getting user location for : " + username));
        // TODO : si UserCurrentLocation peut revenir null, si null alors besoin de trackUSerLocation avec Tracker => vérifier éventuellement si situation probable et nécessaire mais initialement prévue
        return webClientUserApi
                .get()
                .uri("/users/" + username + "/current-location")
                .exchangeToMono(response -> {
                    if(response.statusCode().is2xxSuccessful()) {
                        return response.bodyToMono(VisitedLocation.class);
                    } else if(response.statusCode().equals(HttpStatus.NOT_FOUND)) {
                        throw new ElementNotFoundException("username not found");
                    } else {
                        return response.createException()
                                .flatMap(Mono::error);
                    }
                })
                .block();

    }

    /**
     * @inheritDoc
     */
    @Override
    public User getUser(String username) {
        //TODO : WebClient User API -> UserController -> getUser -> GET : "/users/{username}
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public UserPreferences getUserPreferences(String username) {
        //TODO : WebClient User API -> UserController -> getUserPreferences -> GET : "/users/{username}/preferences
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public UserPreferences updateUserPreferences(String username, UserPreferences updatedPreferences) {
        //TODO : WebClient User API -> UserController -> getUserPreferences -> PUT : "/users/{username}/preferences
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<UserReward> getUserRewards(String username) {
        //TODO : WebClient User API -> UserController -> getUserRewards -> GET : "/users/{username}/rewards
        return null;
    }
}
