package io.traveler.travel.diary.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateReplyRequest(
        @NotBlank String content
) {
}
