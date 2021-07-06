package org.openclassrooms.tourguide.models.model.location;

import java.util.Date;
import java.util.UUID;

public class VisitedLocation {

    private UUID userId;
    private Location location;
    private Date timeVisited;

    public VisitedLocation(final UUID userId1,
                           final Location location1,
                           final Date timeVisited1) {
        userId = userId1;
        location = location1;
        timeVisited = timeVisited1;
    }

    public VisitedLocation() {

    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(final UUID userId1) {
        userId = userId1;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(final Location location1) {
        location = location1;
    }

    public Date getTimeVisited() {
        return timeVisited;
    }

    public void setTimeVisited(final Date timeVisited1) {
        timeVisited = timeVisited1;
    }
}
