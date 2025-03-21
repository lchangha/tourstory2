package io.traveler.travel.trip.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateMessageRequest(
    @NotBlank String message
) {

}
