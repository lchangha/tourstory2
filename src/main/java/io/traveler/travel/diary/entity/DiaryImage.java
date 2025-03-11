package io.traveler.travel.diary.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "diary_images")
@Getter
@NoArgsConstructor
@Where(clause = "is_deleted = false")
public class DiaryImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    private Diary diary;

    @Column(name = "url")
    private String url;

    @Builder
    public DiaryImage(Diary diary, String url) {
        this.diary = diary;
        this.url = url;
    }
}
