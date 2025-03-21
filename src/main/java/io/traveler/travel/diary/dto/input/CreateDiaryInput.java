package io.traveler.travel.diary.dto.input;

import io.traveler.travel.diary.dto.request.CreateDiaryRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.util.List;

public record CreateDiaryInput(
        @NotNull Long tripId,
        @NotBlank String title,
        @NotBlank String content,
        List<byte[]> imageBytesList,
        byte[] thumbnail,
        String email
) {
    public static CreateDiaryInput from(CreateDiaryRequest createDiaryRequest) {
        return new CreateDiaryInput(
                createDiaryRequest.tripId(),
                createDiaryRequest.title(),
                createDiaryRequest.content(),
                null,
                null,
                null
        );
    }

    public static CreateDiaryInput of(CreateDiaryRequest createDiaryRequest, List<byte[]> imageBytesList, byte[] thumbnail, String email) {
        return new CreateDiaryInput(
                createDiaryRequest.tripId(),
                createDiaryRequest.title(),
                createDiaryRequest.content(),
                imageBytesList,
                thumbnail,
                email
        );
    }

    public CreateDiaryInput withImages(List<byte[]> imageBytesList) {
        return new CreateDiaryInput(
                this.tripId,
                this.title,
                this.content,
                imageBytesList,
                this.thumbnail,
                this.email
        );
    }

    public CreateDiaryInput withThumbnail(byte[] thumbnail) {
        return new CreateDiaryInput(
                this.tripId,
                this.title,
                this.content,
                this.imageBytesList,
                thumbnail,
                this.email
        );
    }
}
