package io.traveler.travel.trip.dto.input;

import java.time.*;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import io.traveler.travel.trip.dto.request.CreateTripRequest;
import jakarta.validation.Valid;

public record CreateTripInput(
        String tripName,
        String email,
        LocalDate startDate,
        LocalDate endDate,
        List<Long> cityIds,
        List<Long> conceptIds
) {

    public static CreateTripInput of(UserDetails user, CreateTripRequest createTripRequest) {
        return new CreateTripInput(
            createTripRequest.tripName(),
            user.getUsername(), 
            createTripRequest.startDate(),
            createTripRequest.startDate(),
            createTripRequest.cityIds(),
            createTripRequest.conceptIds()
            );
    }
}
