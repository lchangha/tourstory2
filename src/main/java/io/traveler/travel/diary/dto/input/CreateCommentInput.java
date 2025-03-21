package io.traveler.travel.diary.dto.input;

import io.traveler.travel.diary.dto.request.CreateCommentRequest;

public record CreateCommentInput(
        Long diaryId,
        String email,
        String content
) {
    public static CreateCommentInput from(CreateCommentRequest request) {
        return new CreateCommentInput(null, null, request.content());
    }

    public static CreateCommentInput of(CreateCommentRequest request, Long diaryId, String email) {
        return new CreateCommentInput(diaryId, email, request.content());
    }

    public CreateCommentInput withDiaryId(Long diaryId) {
        return new CreateCommentInput(diaryId, this.email, this.content);
    }

    public CreateCommentInput withUsername(String email) {
        return new CreateCommentInput(this.diaryId, email, this.content);
    }
}
