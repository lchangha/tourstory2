package io.traveler.travel.diary.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateReplyRequest(
        @NotBlank String content
) {

}
