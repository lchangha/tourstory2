package io.traveler.travel.diary.enrtity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "diary_comment")
@Getter
@NoArgsConstructor
public class DiaryComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "diary_id")
    private Long diaryId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Builder
    public DiaryComment(Long diaryId, Long userId, String content, LocalDateTime deletedAt) {
        this.diaryId = diaryId;
        this.userId = userId;
        this.content = content;
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