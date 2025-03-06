package io.traveler.travel.user.dto.response;

import io.traveler.travel.user.entity.User;

public record PublicUserResponse(
    String nickname,
    String profileUrl
) {
    public static PublicUserResponse from(User user) {
        return new PublicUserResponse(user.getNickname(), user.getProfileUrl());
    }
}
