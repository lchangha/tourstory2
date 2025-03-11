package io.traveler.travel.diary.repository;

import io.traveler.travel.diary.entity.Diary;
import io.traveler.travel.diary.entity.DiaryComment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiaryCommentRepository extends JpaRepository<DiaryComment, Long> {
    Slice<DiaryComment> findByDiary(Diary diary, Pageable pageable);


    Optional<DiaryComment> findByIdAndDiary(long commentId, Diary diary);
}
