package io.traveler.travel.user.dto;

import io.traveler.travel.user.entity.User;

public record AuthenticatedUserDTO(
        Long id,
        String email
) {
    public static AuthenticatedUserDTO from(User user) {
        return new AuthenticatedUserDTO(
                user.getId(),
                user.getEmail()
        );
    }
}
