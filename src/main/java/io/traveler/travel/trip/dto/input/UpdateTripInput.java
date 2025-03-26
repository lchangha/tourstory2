package io.traveler.travel.trip.dto.input;

import java.time.*;

public record UpdateTripInput(
        Long tripId,
        String email,
        String tripName,
        LocalDate startDate,
        LocalDate endDate,
        int[] cityIds,
        int[] conceptIds
) {
}
