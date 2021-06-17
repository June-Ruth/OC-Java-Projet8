package org.openclassrooms.tourguide.localization.service;

import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class LocationServiceTest {

    // GET USER LOCATION TESTS //

    /*@Test
    void getExistingUserLocationWithEmptyLocationsTest() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        userService.getUserCurrentLocation(user.getUsername());
        verify(userRepository, times(1)).findByUsername(user.getUsername());
    }

    @Test
    void getExistingUserLocationWithNotEmptyLocationsTest() {
        List<VisitedLocation> visitedLocations = new ArrayList<>();
        visitedLocations.add(new VisitedLocation(user.getUserId(), new Location(2d, 2d), Date.from(Instant.now())));
        user.setVisitedLocations(visitedLocations);
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        userService.getUserCurrentLocation(user.getUsername());
        verify(userRepository, times(1)).findByUsername(user.getUsername());
    }

    @Test
    void getNonExistentUserLocationTest() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        assertThrows(ElementNotFoundException.class, () -> userService.getUserCurrentLocation(user.getUsername()));
        verify(userRepository, times(1)).findByUsername(user.getUsername());
    }*/
}
