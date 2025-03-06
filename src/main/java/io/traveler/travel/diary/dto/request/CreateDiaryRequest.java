package io.traveler.travel.diary.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record CreateDiaryRequest(
        @NotNull Long tripId,
        @NotBlank String title,
        @NotBlank String content,
        List<MultipartFile> images
) {
}
