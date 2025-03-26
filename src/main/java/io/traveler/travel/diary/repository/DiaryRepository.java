package io.traveler.travel.diary.repository;

import io.traveler.travel.diary.entity.*;
import org.springframework.data.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends CrudRepository<Diary, Long> {
}
