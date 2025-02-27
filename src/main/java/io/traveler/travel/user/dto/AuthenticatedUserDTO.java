package io.traveler.travel.user.dto;

public record AuthenticatedUserDTO(
        Long userId,
        String email
) {
}
