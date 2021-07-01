package org.openclassrooms.tourguide.tripapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openclassrooms.tourguide.models.model.trip.Provider;
import org.openclassrooms.tourguide.models.model.user.User;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class TripServiceTest {

    private static TripService tripService;

    private static User user;
    private static final UUID uuid1 = UUID.randomUUID();

    @BeforeEach
    private void beforeEach() {
        tripService = new TripServiceTestImpl();
        user = new User(uuid1, "userName", "phoneNumber", "emailAddress");
    }

    // GET TRIP DEALS TESTS //

    @Test
    void getTripDealsTest() {
        List<Provider> providers = tripService.getTripDeals(user);
        assertNotNull(providers);
        assertEquals(5, providers.size());
    }

}
