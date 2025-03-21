package io.traveler.travel.trip.dto.input;

import org.springframework.data.domain.Pageable;

public record GetMessageInput(
    Long tripId,
    Long messageId,
    Pageable page
)
 {
    public static GetMessageInput of(Long tripId, Long messageId, Pageable page) {
        return new GetMessageInput(
            tripId,
            messageId,
            page
        );
    }
}