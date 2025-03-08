package io.traveler.travel.diary.dto.input;

import io.traveler.travel.diary.dto.request.CreateCommentRequest;

public record CreateCommentInput(
        Long diaryId,
        Long posterId,
        String content
) {
    public static CreateCommentInput from(CreateCommentRequest request) {
        return new CreateCommentInput(null, null, request.content());
    }

    public static CreateCommentInput of(CreateCommentRequest request, Long diaryId, Long posterId) {
        return new CreateCommentInput(diaryId, posterId, request.content());
    }

    public CreateCommentInput withDiaryId(Long diaryId) {
        return new CreateCommentInput(diaryId, this.posterId, this.content);
    }

    public CreateCommentInput withPosterId(Long posterId) {
        return new CreateCommentInput(this.diaryId, posterId, this.content);
    }
}
