package io.traveler.travel.diary.dto.input;

import io.traveler.travel.diary.dto.request.UpdateReplyRequest;

public record UpdateReplyInput(
        Long diaryId,
        Long commentId,
        Long posterId,
        String content
) {
    public static UpdateReplyInput of(UpdateReplyRequest updateReplyRequest, long diaryId, long commentId, long posterId) {
        return new UpdateReplyInput(
                diaryId,
                commentId,
                posterId,
                updateReplyRequest.content()
        );
    }
}
