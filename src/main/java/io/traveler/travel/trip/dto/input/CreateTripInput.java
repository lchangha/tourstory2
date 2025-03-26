package io.traveler.travel.trip.dto.input;

import java.time.*;

public record CreateTripInput(
        String tripName,
        String email,
        LocalDate startDate,
        LocalDate endDate,
        int[] cityIds,
        int[] conceptIds
) {
}
