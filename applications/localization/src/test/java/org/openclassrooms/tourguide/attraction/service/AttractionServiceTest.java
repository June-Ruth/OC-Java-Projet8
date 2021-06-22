package org.openclassrooms.tourguide.attraction.service;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.openclassrooms.tourguide.attraction.exception.ElementNotFoundException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class AttractionServiceTest {

    @Mock
    private GpsUtil gpsUtil;

    private static AttractionService attractionService;

    private static Attraction attraction;
    private static List<Attraction> attractionList = new ArrayList<>();

    @BeforeEach
    void beforeEach() {
        attractionService = new AttractionServiceImpl(gpsUtil);
        attraction = new Attraction("name", "city", "state", 1d, 1d);
        attractionList.add(attraction);
    }

    // GET ATTRACTION TESTS //

    @Test
    void getExistingAttractionTest() {
        when(gpsUtil.getAttractions()).thenReturn(attractionList);
        assertEquals(attraction.attractionName, attractionService.getAttraction(attraction.attractionName).attractionName);
    }

    @Test
    void getNonExistentAttractionTest() {
        when(gpsUtil.getAttractions()).thenReturn(new ArrayList<>());
        assertThrows(ElementNotFoundException.class, () -> attractionService.getAttraction(attraction.attractionName));
    }


}
