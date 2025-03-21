package io.traveler.travel.trip.dto.response;

import io.traveler.travel.user.dto.response.PublicUserResponse;

public record MessageResponse(
    PublicUserResponse sender,
    String message
) {

}
