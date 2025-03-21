package io.traveler.travel.trip.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateMessageRequest(
    @NotBlank String message
) {

}
