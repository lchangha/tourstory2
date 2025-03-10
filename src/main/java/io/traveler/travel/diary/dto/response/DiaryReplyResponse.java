package io.traveler.travel.diary.dto.response;

import io.traveler.travel.diary.entity.DiaryReply;
import io.traveler.travel.user.dto.response.PublicUserResponse;
import java.time.LocalDateTime;

public record DiaryReplyResponse(
        Long id,
        String content,
        PublicUserResponse poster,
        LocalDateTime createdAt
) {
    public static DiaryReplyResponse from(DiaryReply reply) {
        return new DiaryReplyResponse(
                reply.getId(),
                reply.getContent(),
                PublicUserResponse.from(reply.getPoster()),
                reply.getCreatedAt()
        );
    }
}
