package io.traveler.travel.user.dto.request;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public record UpdateUserRequest(
        String email,
        String name,
        String nickname,
        String password,
        MultipartFile profileImage,
        String phone,
        LocalDate birth
) {

}
