package io.traveler.travel.chat.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "message_read")
@Getter
@NoArgsConstructor
public class MessageRead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "trip_message_id")
    private Long tripMessageId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "read_at")
    private LocalDateTime readAt;

    @Builder
    public MessageRead(Long tripMessageId, Long userId) {
        this.tripMessageId = tripMessageId;
        this.userId = userId;
    }

    @PrePersist
    private void onCreate() {
        this.readAt = LocalDateTime.now();
    }
}