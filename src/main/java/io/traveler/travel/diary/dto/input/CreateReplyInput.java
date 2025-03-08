package io.traveler.travel.diary.dto.input;

import io.traveler.travel.diary.dto.request.CreateReplyRequest;

public record CreateReplyInput(
        Long diaryId,
        Long commentId,
        Long posterId,
        String content
) {
    public static CreateReplyInput from(CreateReplyRequest request) {
        return new CreateReplyInput(
                null,
                null,
                null,
                request.content()
        );
    }

    public static CreateReplyInput of(CreateReplyRequest request, long diaryId, long commentId, long posterId) {
        return new CreateReplyInput(diaryId, commentId, posterId, request.content());
    }

    public CreateReplyInput withDiaryId(long diaryId) {
        return new CreateReplyInput(
                diaryId,
                this.commentId,
                this.posterId,
                this.content
        );
    }

    public CreateReplyInput withCommentId(long commentId) {
        return new CreateReplyInput(
                this.diaryId,
                commentId,
                this.posterId,
                this.content
        );
    }

    public CreateReplyInput withPosterId(long posterId) {
        return new CreateReplyInput(
                this.diaryId,
                this.commentId,
                posterId,
                this.content);
    }
}
