package io.traveler.travel.diary.dto.input;

import io.traveler.travel.diary.dto.request.UpdateCommentRequest;

public record UpdateCommentInput(
        Long diaryId,
        Long posterId,
        String content
) {
    public static UpdateCommentInput of(UpdateCommentRequest updateCommentRequest, long diaryId, long posterId) {
        return new UpdateCommentInput(
                diaryId,
                posterId,
                updateCommentRequest.content()
        );
    }
}
