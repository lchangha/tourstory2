package io.traveler.travel.trip.dto.input;

public record CreateMessageInput(
        Long tripId,
        Long messageId,
        String email,
        String message) {
    public static CreateMessageInput of(Long tripId, Long messageId, String email, String message) {
        return new CreateMessageInput(
            tripId,
            messageId,
            email,
            message
        );
    }
}
