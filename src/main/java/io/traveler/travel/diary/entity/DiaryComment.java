package io.traveler.travel.diary.entity;

import io.traveler.travel.common.entity.TimeTrackedEntity;
import io.traveler.travel.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "diary_comment")
@Getter
@NoArgsConstructor
public class DiaryComment extends TimeTrackedEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    private Diary diary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User poster;

    @Column(name = "content")
    private String content;

    @Builder
    public DiaryComment(Diary diary, User poster, String content) {
        this.diary = diary;
        this.poster = poster;
        this.content = content;
    }

    public void updateContent(String content) {
        this.content = content;
    }

}
