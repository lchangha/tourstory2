package io.traveler.travel.trip.dto.request;

import jakarta.validation.constraints.*;

import java.time.*;

public record CreateTripRequest(
        @NotBlank String tripName,
        @NotBlank LocalDate startDate,
        @NotBlank LocalDate endDate,
        @NotBlank int[] cityIds,
        @NotBlank int[] conceptIds
) {
}
