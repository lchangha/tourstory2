package io.traveler.travel.trip.dto.input;

import java.time.*;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

import io.traveler.travel.trip.dto.request.UpdateTripRequest;

public record UpdateTripInput(
        Long tripId,
        String email,
        Optional<String> tripName,
        Optional<LocalDate> startDate,
        Optional<LocalDate> endDate,
        Optional<List<Long>> cityIds,
        Optional<List<Long>> conceptIds
) {

    public static UpdateTripInput of(UserDetails user, UpdateTripRequest updateTripRequest) {
        return new UpdateTripInput(
            updateTripRequest.tripId(),
            user.getUsername(),
            updateTripRequest.tripName(),
            updateTripRequest.startDate(),
            updateTripRequest.endDate(),
            updateTripRequest.cityIds(), 
            updateTripRequest.conceptIds()
            );
    }
}
