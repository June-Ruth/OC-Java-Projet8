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
import reactor.core.publisher.Flux;
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
        return webClientUserApi
                .get()
                .uri("/users/" + username + "/current-location")
                .exchangeToMono(clientResponse -> {
                    if(clientResponse.statusCode().equals(HttpStatus.OK)) {
                        return clientResponse.bodyToMono(VisitedLocation.class);
                    } else if(clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
                        throw new ElementNotFoundException("username : " + username + " not found");
                    } else {
                        return clientResponse.createException()
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
        LOGGER.info("Getting user with username : " + username);
        return webClientUserApi
                .get()
                .uri("/users/" + username)
                .exchangeToMono(clientResponse -> {
                    if(clientResponse.statusCode().equals(HttpStatus.OK)) {
                        return clientResponse.bodyToMono(User.class);
                    } else if(clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
                        throw new ElementNotFoundException("username : " + username + " not found");
                    } else {
                        return clientResponse.createException()
                                .flatMap(Mono::error);
                    }
                })
                .block();
    }

    /**
     * @inheritDoc
     */
    @Override
    public UserPreferences getUserPreferences(String username) {
        LOGGER.info("Getting user preferences for user : " + username);
        return webClientUserApi
                .get()
                .uri("/users/" + username + "/preferences")
                .exchangeToMono(clientResponse -> {
                    if(clientResponse.statusCode().equals(HttpStatus.OK)) {
                        return clientResponse.bodyToMono(UserPreferences.class);
                    } else if(clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
                        throw new ElementNotFoundException("username : " + username + " not found");
                    } else {
                        return clientResponse.createException()
                                .flatMap(Mono::error);
                    }
                })
                .block();
    }

    /**
     * @inheritDoc
     */
    @Override
    public UserPreferences updateUserPreferences(String username, UserPreferences updatedPreferences) {
        LOGGER.info("Update user : " + username + " with updated preferences : " + updatedPreferences);
        return webClientUserApi
                .put()
                .uri("/users/" + username + "/preferences")
                .body(Mono.just(updatedPreferences), UserPreferences.class)
                .exchangeToMono(clientResponse -> {
                    if(clientResponse.statusCode().equals(HttpStatus.OK)) {
                        return clientResponse.bodyToMono(UserPreferences.class);
                    } else if(clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
                        throw new ElementNotFoundException("username : " + username + " not found");
                    } else {
                        return clientResponse.createException()
                                .flatMap(Mono::error);
                    }
                })
                .block();
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<UserReward> getUserRewards(String username) {
        LOGGER.info("Getting user rewards for user : " + username);
        return webClientUserApi
                .get()
                .uri("/users/" + username + "/rewards")
                .exchangeToFlux(clientResponse -> {
                    if(clientResponse.statusCode().equals(HttpStatus.OK)) {
                        return clientResponse.bodyToFlux(UserReward.class);
                    } else if(clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
                        throw new ElementNotFoundException("username : " + username + " not found");
                    } else {
                        return clientResponse.createException()
                                .flatMapMany(Flux::error);
                    }
                })
                .collectList()
                .block();
    }
}
