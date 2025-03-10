package io.traveler.travel.diary.dto.input;

import io.traveler.travel.diary.dto.request.UpdateReplyRequest;

public record UpdateReplyInput(
        Long diaryId,
        Long commentId,
        Long replyId,
        String content,
        Long posterId
) {
    public static UpdateReplyInput of(UpdateReplyRequest updateReplyRequest, long diaryId, long commentId, long replyId, long posterId) {
        return new UpdateReplyInput(
                diaryId,
                commentId,
                replyId,
                updateReplyRequest.content(),
                posterId
        );
    }
}
