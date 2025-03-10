package io.traveler.travel.diary.repository;

import io.traveler.travel.diary.entity.DiaryComment;
import io.traveler.travel.diary.entity.DiaryReply;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface DiaryReplyRepository extends JpaRepository<DiaryReply, Long> {
    Slice<DiaryReply> findByDiaryComment(DiaryComment comment, Pageable pageable);

    Optional<DiaryReply> findByDiaryComment(DiaryComment comment);
}
