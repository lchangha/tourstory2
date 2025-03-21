package io.traveler.travel.diary.dto.input;

import io.traveler.travel.diary.dto.request.UpdateReplyRequest;

public record UpdateReplyInput(
        Long diaryId,
        Long commentId,
        Long replyId,
        String content,
        String email
) {
    public static UpdateReplyInput of(UpdateReplyRequest updateReplyRequest, long diaryId, long commentId, long replyId, String email) {
        return new UpdateReplyInput(
                diaryId,
                commentId,
                replyId,
                updateReplyRequest.content(),
                email
        );
    }
}
