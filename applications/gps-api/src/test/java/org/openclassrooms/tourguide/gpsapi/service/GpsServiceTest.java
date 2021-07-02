package org.openclassrooms.tourguide.gpsapi.service;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.openclassrooms.tourguide.gpsapi.exception.ElementNotFoundException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class GpsServiceTest {

    @Mock
    private GpsUtil gpsUtil;

    private static GpsService gpsService;

    private static Attraction attraction;
    private static List<Attraction> attractionList = new ArrayList<>();

    @BeforeEach
    void beforeEach() {
        gpsService = new GpsServiceImpl(gpsUtil);
        attraction = new Attraction("name", "city", "state", 1d, 1d);
        attractionList.add(attraction);
    }

    // GET ATTRACTION TESTS //

    @Test
    void getExistingAttractionTest() {
        when(gpsUtil.getAttractions()).thenReturn(attractionList);
        assertEquals(attraction.attractionName, gpsService.getAttraction(attraction.attractionName).getAttractionName());
    }

    @Test
    void getNonExistentAttractionTest() {
        when(gpsUtil.getAttractions()).thenReturn(new ArrayList<>());
        assertThrows(ElementNotFoundException.class, () -> gpsService.getAttraction(attraction.attractionName));
    }

}
