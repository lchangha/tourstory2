package io.traveler.travel.trip.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.*;

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
    private void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
