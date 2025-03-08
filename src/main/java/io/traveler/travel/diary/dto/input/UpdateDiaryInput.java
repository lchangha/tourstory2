package io.traveler.travel.diary.dto.input;

import io.traveler.travel.diary.dto.request.UpdateDiaryRequest;

import java.util.List;

public record UpdateDiaryInput(
        Long id,
        Long posterId,
        String title,
        String content,
        List<byte[]> imageBytesList,
        byte[] thumbnail
) {

    public static UpdateDiaryInput of(UpdateDiaryRequest updateDiaryRequest, long diaryId, List<byte[]> imageBytesList, byte[] thumbnail, long posterId) {
        return new UpdateDiaryInput(
                diaryId,
                posterId,
                updateDiaryRequest.title(),
                updateDiaryRequest.content(),
                imageBytesList,
                thumbnail
        );
    }
    public static UpdateDiaryInput from(UpdateDiaryRequest updateDiaryRequest) {
        return new UpdateDiaryInput(
                null,
                null,
                updateDiaryRequest.title(),
                updateDiaryRequest.content(),
                null,
                null
        );
    }

    public UpdateDiaryInput withId(long diaryId) {
        return new UpdateDiaryInput(
                diaryId,
                this.posterId,
                this.title,
                this.content,
                this.imageBytesList,
                this.thumbnail
        );
    }

    public UpdateDiaryInput withImages(List<byte[]> imageBytesList) {
        return new UpdateDiaryInput(
                this.id,
                this.posterId,
                this.title,
                this.content,
                imageBytesList,
                this.thumbnail
        );
    }
}
