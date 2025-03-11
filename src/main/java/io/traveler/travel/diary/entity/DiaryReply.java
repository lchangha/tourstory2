package io.traveler.travel.diary.entity;

import io.traveler.travel.common.entity.TimeTrackedEntity;
import io.traveler.travel.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "diary_reply")
@Getter
@NoArgsConstructor
@Where(clause = "is_deleted = false")
public class DiaryReply extends TimeTrackedEntity {

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User poster;

    @JoinColumn(name = "diary_comment_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private DiaryComment comment;

    @Column(name = "content")
    private String content;

    @Builder
    public DiaryReply(User poster, String content) {
        this.poster = poster;
        this.content = content;
    }

    public void updateContent(String content) {
        this.content = content;
    }

}
