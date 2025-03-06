package io.traveler.travel.diary.repository;

import io.traveler.travel.diary.entity.Diary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends CrudRepository<Diary, Long> {
    Slice<Diary> findByIsDeletedFalse(Pageable pageable);
}
