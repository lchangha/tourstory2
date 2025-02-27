package io.traveler.travel.diary.enrtity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "diaries")
@Getter
@NoArgsConstructor
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "trips_id")
    private Long tripsId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Builder
    public Diary(Long userId, Long tripsId, String title, String content, String thumbnailUrl, LocalDateTime deletedAt) {
        this.userId = userId;
        this.tripsId = tripsId;
        this.title = title;
        this.content = content;
        this.thumbnailUrl = thumbnailUrl;
        this.deletedAt = deletedAt;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
