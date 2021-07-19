package org.openclassrooms.tourguide.webapp.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.openclassrooms.tourguide.models.model.location.Location;
import org.openclassrooms.tourguide.models.model.location.VisitedLocation;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@Disabled
//TODO : unit test on services
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @Mock
    private WebClient mockWebClientUserApi;

    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpecMock;

    @Mock
    private WebClient.RequestBodySpec requestBodySpecMock;

    @SuppressWarnings("rawtypes")
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpecMock;

    @SuppressWarnings("rawtypes")
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock;

    @Mock
    private WebClient.ResponseSpec responseSpecMock;

    @Mock
    private ClientResponse clientResponseMock;


    private static UserService userService;

    private static VisitedLocation visitedLocation1;
    private static VisitedLocation visitedLocation2;
    private static List<VisitedLocation> visitedLocationList;

    @BeforeEach
    void beforeAll() {
        userService = new UserServiceImpl(mockWebClientUserApi);
        visitedLocation1 = new VisitedLocation(UUID.randomUUID(), new Location(1d, 1d), Date.from(Instant.now()));
        visitedLocation2 = new VisitedLocation(UUID.randomUUID(), new Location(2d, 2d), Date.from(Instant.now()));
        visitedLocationList = new ArrayList<>();
        visitedLocationList.add(visitedLocation1);
        visitedLocationList.add(visitedLocation2);
    }

    @AfterEach
    void afterEach() {
        visitedLocationList.clear();
    }

    // GET ALL USER CURRENT LOCATIONS TESTS //

    @Test
    void getAllUserCurrentLocationsTest() {
/*
        when(mockWebClientUserApi).thenReturn()

        List<VisitedLocation> result = userService.getAllUserCurrentLocations();

        assertEquals(2, result.size());
 */

    }

    // GET USER CURRENT LOCATION TEST //

    @SuppressWarnings("unchecked")
    @Test
    void getExistingUserCurrentLocationTest() {
        when(mockWebClientUserApi.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestBodyUriSpecMock.uri(anyString())).thenReturn(requestBodySpecMock);
        when(requestHeadersSpecMock.exchangeToMono(any(Function.class))).thenReturn(Mono.just(visitedLocation1));

        VisitedLocation result = userService.getUserCurrentLocation("username");
        assertNull(result.getLocation());
    }

}
