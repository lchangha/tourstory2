package io.traveler.travel.user.dto.input;

import io.traveler.travel.user.dto.request.UpdateUserRequest;

import java.time.LocalDate;

public record UpdateUserInput(
        Long id,
        String email,
        String name,
        String nickname,
        String password,
        byte[] profileImage,
        String phone,
        LocalDate birth
) {
    public static UpdateUserInput from(UpdateUserRequest request) {
        return new UpdateUserInput(
                null,
                request.email(),
                request.name(),
                request.nickname(),
                request.password(),
                null,
                request.phone(),
                request.birth()
        );
    }

    public UpdateUserInput withId(long id) {
        return new UpdateUserInput(
                id,
                this.email,
                this.name,
                this.nickname,
                this.password,
                this.profileImage,
                this.phone,
                this.birth
        );

    }

    public UpdateUserInput withProfileImage(byte[] profileImage) {
        return new UpdateUserInput(
                this.id,
                this.email,
                this.name,
                this.nickname,
                this.password,
                profileImage,
                this.phone,
                this.birth
        );
    }
}
