package io.traveler.travel.diary.entity;

import io.traveler.travel.common.entity.TimeTrackedEntity;
import io.traveler.travel.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "diary_comment")
@Getter
@NoArgsConstructor
public class DiaryComment extends TimeTrackedEntity {

    @Column(name = "diary_id")
    private Long diaryId;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User poster;

    @Column(name = "content")
    private String content;

    @Builder
    public DiaryComment(Long diaryId, User poster, String content) {
        this.diaryId = diaryId;
        this.poster = poster;
        this.content = content;
    }
}
