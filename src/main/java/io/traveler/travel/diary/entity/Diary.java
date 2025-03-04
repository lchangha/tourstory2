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
@Table(name = "diaries")
@Getter
@NoArgsConstructor
public class Diary extends TimeTrackedEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User poster;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    private List<DiaryImage> diaryImages = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    private List<DiaryComment> diaryComments = new ArrayList<>();

    @Column(name = "trips_id")
    private Long tripsId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Builder
    public Diary(User poster, Long tripsId, String title, String content, String thumbnailUrl) {
        this.poster = poster;
        this.tripsId = tripsId;
        this.title = title;
        this.content = content;
        this.thumbnailUrl = thumbnailUrl;
    }

    public void addComment(DiaryComment comment) {
        diaryComments.add(comment);
    }

    public void addImage(DiaryImage image) {
        diaryImages.add(image);
    }
}
