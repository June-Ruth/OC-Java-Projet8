package tourGuide.helper;

import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.openclassrooms.tourguide.models.User;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.IntStream;

@Profile("test") //TODO : voir si a un intérêt quelconque si pas de bean
public class InternalTestInitializer {
    /**
     * @see Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(InternalTestInitializer.class);

    /**
     * Initialize internal users for testing.
     * @see InternalTestHelper which indicates numnber of user to create
     */
    public Map<String, User> initializeInternalUsers() {
        Map<String, User> internalUserMap = new HashMap<>();
        IntStream.range(0, InternalTestHelper.getInternalUserNumber()).forEach(i -> {
            String userName = "internalUser" + i;
            String phone = "000";
            String email = userName + "@tourGuide.com";
            User user = new User(UUID.randomUUID(), userName, phone, email);
            generateUserLocationHistory(user);

            internalUserMap.put(userName, user);
        });
        LOGGER.debug("Created " + InternalTestHelper.getInternalUserNumber() + " internal test users.");
        return internalUserMap;
    }

    /**
     * Generate user location history for the initialization.
     * @param user .
     */
    private void generateUserLocationHistory(User user) {
        IntStream.range(0, 3).forEach(i-> addVisitedLocation(user, new VisitedLocation(user.getUserId(), new Location(generateRandomLatitude(), generateRandomLongitude()), getRandomTime())));
    }

    /**
     * Generate a random longitude for initialization.
     * @return a random longitude as double
     */
    private double generateRandomLongitude() {
        double leftLimit = -180;
        double rightLimit = 180;
        return leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
    }

    /**
     * Generate a random latitude for initialization.
     * @return a random latitude as double
     */
    private double generateRandomLatitude() {
        double leftLimit = -85.05112878;
        double rightLimit = 85.05112878;
        return leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
    }

    /**
     * Get a random time for initialization.
     * @return a random Date
     */
    private Date getRandomTime() {
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(new Random().nextInt(30));
        return Date.from(localDateTime.toInstant(ZoneOffset.UTC));
    }

    private void addVisitedLocation(User user, VisitedLocation visitedLocation) {
        List<VisitedLocation> visitedLocations = user.getVisitedLocations();
        visitedLocations.add(visitedLocation);
    }
}
