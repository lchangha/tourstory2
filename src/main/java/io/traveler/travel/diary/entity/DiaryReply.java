package io.traveler.travel.diary.entity;

import io.traveler.travel.common.entity.TimeTrackedEntity;
import io.traveler.travel.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "diary_reply")
@Getter
@NoArgsConstructor
public class DiaryReply extends TimeTrackedEntity {

    @Column(name = "diary_comment_id")
    private Long diaryCommentId;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User poster;

    @Column(name = "content")
    private String content;

    @Builder
    public DiaryReply(Long diaryCommentId, User poster, String content) {
        this.diaryCommentId = diaryCommentId;
        this.poster = poster;
        this.content = content;
    }
}
