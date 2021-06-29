package org.openclassrooms.tourguide.webapp.service;

import org.openclassrooms.tourguide.models.model.location.Attraction;
import org.openclassrooms.tourguide.models.model.location.Location;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    /**
     * @inheritDoc
     */
    @Override
    public Attraction getAttraction (final String attractionName) {
        //TODO
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Attraction> getFiveClosestAttractions(final Location location) {
        //TODO
        return null;
    }
}
