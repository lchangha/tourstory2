package io.traveler.travel.common.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "notification")
@Getter
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "receiver_id")
    private Long receiverId;

    @Column(name = "trigger_user_id")
    private Long triggerUserId;

    @Column(name = "target_id")
    private Long targetId;

    @Column(name = "type")
    private String type;

    @Column(name = "content")
    private String content;

    @Column(name = "url")
    private String url;

    @Column(name = "un_read")
    private Boolean unRead;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public Notification(Long receiverId, Long triggerUserId, Long targetId, String type, String content, String url) {
        this.receiverId = receiverId;
        this.triggerUserId = triggerUserId;
        this.targetId = targetId;
        this.type = type;
        this.content = content;
        this.url = url;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        if (this.unRead == null) {
            this.unRead = true;
        }
    }
}