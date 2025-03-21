package io.traveler.travel.diary.dto.input;

import io.traveler.travel.diary.dto.request.CreateReplyRequest;

public record CreateReplyInput(
        Long diaryId,
        Long commentId,
        String email,
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

    public static CreateReplyInput of(CreateReplyRequest request, long diaryId, long commentId, String email) {
        return new CreateReplyInput(diaryId, commentId, email, request.content());
    }

    public CreateReplyInput withDiaryId(long diaryId) {
        return new CreateReplyInput(
                diaryId,
                this.commentId,
                this.email,
                this.content
        );
    }

    public CreateReplyInput withCommentId(long commentId) {
        return new CreateReplyInput(
                this.diaryId,
                commentId,
                this.email,
                this.content
        );
    }

    public CreateReplyInput withUsername(String email) {
        return new CreateReplyInput(
                this.diaryId,
                this.commentId,
                email,
                this.content);
    }
}
