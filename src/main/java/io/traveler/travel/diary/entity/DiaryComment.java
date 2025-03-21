package io.traveler.travel.diary.entity;

import io.traveler.travel.global.entity.TimeTrackedEntity;
import io.traveler.travel.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "diary_comment")
@Getter
@NoArgsConstructor
@Where(clause = "is_deleted = false")
public class DiaryComment extends TimeTrackedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
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
