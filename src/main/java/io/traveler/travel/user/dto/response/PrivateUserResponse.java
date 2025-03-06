package io.traveler.travel.user.dto.response;

import io.traveler.travel.user.entity.User;

import java.time.LocalDateTime;

public record PrivateUserResponse(
    String name,
    String nickname,
    String email,
    String profileUrl,
    String gender,
    String phone,
    LocalDateTime createdAt

) {
    public static PrivateUserResponse from(User user) {
        return new PrivateUserResponse(
                user.getName(),
                user.getNickname(),
                user.getEmail(),
                user.getProfileUrl(),
                user.getGender(),
                user.getPhone(),
                user.getCreatedAt()
                );
    }
}
