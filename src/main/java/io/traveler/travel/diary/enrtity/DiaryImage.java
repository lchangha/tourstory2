package io.traveler.travel.diary.enrtity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "diary_images")
@Getter
@NoArgsConstructor
public class DiaryImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "diary_id")
    private Long diaryId;

    @Column(name = "url")
    private String url;

    @Builder
    public DiaryImage(Long diaryId, String url) {
        this.diaryId = diaryId;
        this.url = url;
    }
}