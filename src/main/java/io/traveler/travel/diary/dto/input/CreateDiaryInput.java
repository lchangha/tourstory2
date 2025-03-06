package io.traveler.travel.diary.dto.input;

import io.traveler.travel.diary.dto.request.CreateDiaryRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.util.List;

public record CreateDiaryInput(
        Long tripId,
        String title,
        String content,
        List<byte[]> imageBytesList
) {
    public static CreateDiaryInput from(CreateDiaryRequest createDiaryRequest) {
        return new CreateDiaryInput(
                createDiaryRequest.tripId(),
                createDiaryRequest.title(),
                createDiaryRequest.content(),
                null
        );
    }

    public CreateDiaryInput withImages(List<byte[]> imageBytesList) {
        return new CreateDiaryInput(
                this.tripId,
                this.title,
                this.content,
                imageBytesList
        );
    }
}
