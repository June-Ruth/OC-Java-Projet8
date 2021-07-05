package org.openclassrooms.tourguide.gpsapi.service;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.openclassrooms.tourguide.gpsapi.exception.ElementNotFoundException;
import org.openclassrooms.tourguide.models.model.location.Location;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class GpsServiceTest {

    @Mock
    private GpsUtil gpsUtil;

    private static GpsService gpsService;

    private static Attraction attraction1;
    private static Attraction attraction2;
    private static Attraction attraction3;
    private static Attraction attraction4;
    private static Attraction attraction5;
    private static Attraction attraction6;
    private static Attraction attraction7;
    private static Attraction attraction8;
    private static Attraction attraction9;
    private static Attraction attraction10;

    private static List<Attraction> attractionList = new ArrayList<>();

    @BeforeEach
    void beforeEach() {
        gpsService = new GpsServiceImpl(gpsUtil);
        attraction1 = new Attraction("name1", "city1", "state1", 1d, 1d);
        attraction2 = new Attraction("name2", "city2", "state2", 2d, 2d);
        attraction3 = new Attraction("name3", "city3", "state3", 3d, 3d);
        attraction4 = new Attraction("name4", "city4", "state4", 4d, 4d);
        attraction5 = new Attraction("name5", "city5", "state5", 5d, 5d);
        attraction6 = new Attraction("name6", "city6", "state6", 6d, 6d);
        attraction7 = new Attraction("name7", "city7", "state7", 7d, 7d);
        attraction8 = new Attraction("name8", "city8", "state8", 8d, 8d);
        attraction9 = new Attraction("name9", "city9", "state9", 9d, 9d);
        attraction10 = new Attraction("name10", "city10", "state10", 10d, 10d);
        attractionList.add(attraction1);
        attractionList.add(attraction2);
        attractionList.add(attraction3);
        attractionList.add(attraction4);
        attractionList.add(attraction5);
        attractionList.add(attraction6);
        attractionList.add(attraction7);
        attractionList.add(attraction8);
        attractionList.add(attraction9);
        attractionList.add(attraction10);
    }

    // GET ATTRACTION TESTS //

    @Test
    void getExistingAttractionTest() {
        when(gpsUtil.getAttractions()).thenReturn(attractionList);
        assertEquals(attraction1.attractionName, gpsService.getAttraction(attraction1.attractionName).getAttractionName());
    }

    @Test
    void getNonExistentAttractionTest() {
        when(gpsUtil.getAttractions()).thenReturn(new ArrayList<>());
        assertThrows(ElementNotFoundException.class, () -> gpsService.getAttraction(attraction1.attractionName));
    }



}
