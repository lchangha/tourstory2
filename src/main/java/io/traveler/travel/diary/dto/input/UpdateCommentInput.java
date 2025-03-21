package io.traveler.travel.diary.dto.input;

import io.traveler.travel.diary.dto.request.UpdateCommentRequest;

public record UpdateCommentInput(
        Long diaryId,
        Long commentId,
        String email,
        String content
) {
    public static UpdateCommentInput of(UpdateCommentRequest updateCommentRequest, long diaryId, long commentId, String email) {
        return new UpdateCommentInput(
                diaryId,
                commentId,
                email,
                updateCommentRequest.content()
        );
    }
}
