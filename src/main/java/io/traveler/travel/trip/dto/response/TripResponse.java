package io.traveler.travel.trip.dto.response;

import io.traveler.travel.trip.entity.*;

public record TripResponse(

) {
    public static TripResponse from(Trip trip) {
        return new TripResponse(

        );
    }
}
