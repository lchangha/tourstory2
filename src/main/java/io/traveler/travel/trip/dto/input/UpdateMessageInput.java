package io.traveler.travel.trip.dto.input;

public record UpdateMessageInput(
    Long tripId,
    Long messageId,
    String email,
    String message
) {
    public static UpdateMessageInput of(Long tripId, Long messageId, String email, String message) {
        return new UpdateMessageInput(
            tripId,
            messageId,
            email,
            message
        );
    }
}
