package io.traveler.travel.diary.entity;

import io.traveler.travel.common.entity.TimeTrackedEntity;
import io.traveler.travel.trip.entity.Trip;
import io.traveler.travel.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "diaries")
@Getter
@NoArgsConstructor
public class Diary extends TimeTrackedEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User poster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trips_id")
    private Trip trip;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Builder
    public Diary(User poster, Trip trip, String title, String content, String thumbnailUrl) {
        this.poster = poster;
        this.trip = trip;
        this.title = title;
        this.content = content;
        this.thumbnailUrl = thumbnailUrl;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void updateThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
