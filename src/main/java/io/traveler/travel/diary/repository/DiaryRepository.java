package io.traveler.travel.diary.repository;

import io.traveler.travel.diary.entity.*;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
}
