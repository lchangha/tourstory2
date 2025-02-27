package io.traveler.travel.chat.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "chat_participants")
@Getter
@NoArgsConstructor
public class ChatParticipant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "trips_id")
    private Long tripsId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public ChatParticipant(Long tripsId, Long userId) {
        this.tripsId = tripsId;
        this.userId = userId;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
