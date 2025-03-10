package io.traveler.travel.diary.dto.input;

import io.traveler.travel.diary.dto.request.UpdateCommentRequest;

public record UpdateCommentInput(
        Long diaryId,
        Long commentId,
        Long posterId,
        String content
) {
    public static UpdateCommentInput of(UpdateCommentRequest updateCommentRequest, long diaryId, long commentId, long posterId) {
        return new UpdateCommentInput(
                diaryId,
                commentId,
                posterId,
                updateCommentRequest.content()
        );
    }
}
