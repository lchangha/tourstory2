package io.traveler.travel.user.dto.request;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CreateUserRequest(
        @NotBlank()
        @Email()
        String email,

        @NotBlank()
        String name,

        @NotBlank()
        String nickname,

        @NotBlank()
        String password,

        @NotBlank()
        String phone,

        @NotNull()
        LocalDate birth,

        @NotBlank()
        String gender
) {}