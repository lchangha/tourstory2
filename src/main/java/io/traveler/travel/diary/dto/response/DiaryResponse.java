package io.traveler.travel.diary.dto.response;

import io.traveler.travel.diary.entity.Diary;
import io.traveler.travel.user.dto.response.PublicUserResponse;

import java.time.LocalDateTime;
import java.util.List;

public record DiaryResponse(
        String title,
        String content,
        String thumbnailUrl,
        PublicUserResponse poster,
        List<String> imageUrls,
        LocalDateTime createdAt
) {
    public static DiaryResponse from(Diary diary) {
        return new DiaryResponse(
                diary.getTitle(),
                diary.getContent(),
                diary.getThumbnailUrl(),
                PublicUserResponse.from(diary.getPoster()),
                null,
                diary.getCreatedAt()
        );
    }

    public DiaryResponse withImagesUrl(List<String> imagesUrl) {
        return new DiaryResponse(
                title,
                content,
                thumbnailUrl,
                poster,
                imagesUrl,
                createdAt
        );
    }
}
