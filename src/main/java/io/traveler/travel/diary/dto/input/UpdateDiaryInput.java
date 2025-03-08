package io.traveler.travel.diary.dto.input;

import io.traveler.travel.diary.dto.request.UpdateDiaryRequest;

import java.util.List;

public record UpdateDiaryInput(
        Long id,
        String title,
        String content,
        List<byte[]> imageBytesList,
        byte[] thumbnail
) {
    public static UpdateDiaryInput from(UpdateDiaryRequest updateDiaryRequest) {
        return new UpdateDiaryInput(
                null,
                updateDiaryRequest.title(),
                updateDiaryRequest.content(),
                null,
                null
        );
    }

    public UpdateDiaryInput WithId(Long id) {
        return new UpdateDiaryInput(
                id,
                this.title,
                this.content,
                this.imageBytesList,
                this.thumbnail
        );
    }

    public UpdateDiaryInput withImages(List<byte[]> imageBytesList) {
        return new UpdateDiaryInput(
                this.id,
                this.title,
                this.content,
                imageBytesList,
                this.thumbnail
        );
    }
}
