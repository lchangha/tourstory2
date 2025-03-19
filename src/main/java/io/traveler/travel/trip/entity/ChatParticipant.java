package io.traveler.travel.trip.entity;

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

    // insertable을 false로 해서 db의 DEFAULT CURRENT_TIMESTAMP를 쓸지 createAt = LocalDateTime.now()를 쓸지(혹은 앞의 두 방법 모두를 사용하여 테스트 가능하게 만들지)
    // @prepersist를 쓸지 고민했었는데 현재는 이렇게
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
