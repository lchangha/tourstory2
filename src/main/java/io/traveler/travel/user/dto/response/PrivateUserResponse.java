package io.traveler.travel.user.dto.response;

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
}
