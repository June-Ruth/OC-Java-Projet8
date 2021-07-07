package org.openclassrooms.tourguide.webapp.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class LocationServiceTest {

    /*private static LocationService locationService;

    private static gpsUtil.location.Attraction attraction1;
    private static gpsUtil.location.Attraction attraction2;
    private static gpsUtil.location.Attraction attraction3;
    private static gpsUtil.location.Attraction attraction4;
    private static gpsUtil.location.Attraction attraction5;
    private static gpsUtil.location.Attraction attraction6;
    private static gpsUtil.location.Attraction attraction7;
    private static gpsUtil.location.Attraction attraction8;
    private static gpsUtil.location.Attraction attraction9;
    private static gpsUtil.location.Attraction attraction10;

    private static List<gpsUtil.location.Attraction> attractionList = new ArrayList<>();

    @BeforeEach
    void beforeEach() {
        locationService = new LocationServiceImpl();
        attraction1 = new gpsUtil.location.Attraction("name1", "city1", "state1", 1d, 1d);
        attraction2 = new gpsUtil.location.Attraction("name2", "city2", "state2", 2d, 2d);
        attraction3 = new gpsUtil.location.Attraction("name3", "city3", "state3", 3d, 3d);
        attraction4 = new gpsUtil.location.Attraction("name4", "city4", "state4", 4d, 4d);
        attraction5 = new gpsUtil.location.Attraction("name5", "city5", "state5", 5d, 5d);
        attraction6 = new gpsUtil.location.Attraction("name6", "city6", "state6", 6d, 6d);
        attraction7 = new gpsUtil.location.Attraction("name7", "city7", "state7", 7d, 7d);
        attraction8 = new gpsUtil.location.Attraction("name8", "city8", "state8", 8d, 8d);
        attraction9 = new gpsUtil.location.Attraction("name9", "city9", "state9", 9d, 9d);
        attraction10 = new gpsUtil.location.Attraction("name10", "city10", "state10", 10d, 10d);
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

    // GET FIVE NEAREST ATTRACTIONS TEST //

    @Test
    void getFiveNearestAttractionsTest() {
        when(gpsUtil.getAttractions()).thenReturn(attractionList);
        Map<Double, Attraction> result = gpsService.getFiveNearestAttractions(new Location(1d, 1d));
        assertEquals(5, result.size());
    }*/
}
