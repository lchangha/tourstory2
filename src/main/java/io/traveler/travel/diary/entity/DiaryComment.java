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

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User poster;

    @JoinColumn(name = "diary_comment_id")
    @OneToMany(fetch = FetchType.LAZY)
    private List<DiaryReply> diaryReplies = new ArrayList<>();

    @Column(name = "content")
    private String content;

    @Builder
    public DiaryComment(User poster, String content) {
        this.poster = poster;
        this.content = content;
    }

    public void addDiaryReply(DiaryReply diaryReply) {
        diaryReplies.add(diaryReply);
    }
}
