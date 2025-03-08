package io.traveler.travel.diary.dto.request;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record UpdateDiaryRequest(
        String title,
        String content,
        List<MultipartFile> images,
        MultipartFile thumbnail
) {
}
