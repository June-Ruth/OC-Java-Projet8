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
        //TODO : WebClient Gps API -> GpsController -> getAttractionInformation -> GET : "/attractions/{attractionName}"
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Attraction> getFiveClosestAttractions(final Location location) {
        //TODO : WebClient Gps API - GPSController -> getFiveClosestAttractions -> GET : "/attractions/closest-five"

        /*
        EXEMPLE WEBCLIENT
        double userCurrentLatitude = getUserCurrentLocation(username).location.latitude;
        double userCurrentLongitude = getUserCurrentLocation(username).location.longitude;
        List<Attraction> fiveNearestAttraction = new ArrayList<>();

        List<Attraction> fiveClosestAttractions = webClientAttraction.get()
                .uri("/attractions/closest-five?latitude=" + userCurrentLatitude + "&longitude=" + userCurrentLongitude)
                .retrieve().bodyToFlux(Attraction.class).collectList().block();
        return fiveClosestAttractions;*/

        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public double getUserDistanceFromAttraction(final Location userLocation,
                                                final double attractionLatitude,
                                                final double attractionLongitude) {
        //TODO
        return 0;
    }
}
