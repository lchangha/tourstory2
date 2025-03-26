package io.traveler.travel.trip.dto.request;

import jakarta.validation.constraints.*;

import java.time.*;
import java.util.List;

public record CreateTripRequest(
        @NotBlank String tripName,
        @NotBlank LocalDate startDate,
        @NotBlank LocalDate endDate,
        @NotBlank List<Long> cityIds,
        @NotBlank List<Long> conceptIds
) {
}
