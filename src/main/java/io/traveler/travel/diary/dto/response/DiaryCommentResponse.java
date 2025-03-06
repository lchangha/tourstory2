package io.traveler.travel.diary.dto.response;

import io.traveler.travel.diary.entity.DiaryComment;
import io.traveler.travel.user.dto.response.PublicUserResponse;

import java.time.LocalDateTime;

public record DiaryCommentResponse(
        Long id,
        String content,
        PublicUserResponse poster,
        LocalDateTime createdAt
) {
    public static DiaryCommentResponse from(DiaryComment comment) {
        return new DiaryCommentResponse(
                comment.getId(),
                comment.getContent(),
                PublicUserResponse.from(comment.getPoster()),
                comment.getCreatedAt()
        );
    }
}
