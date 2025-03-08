package io.traveler.travel.diary.service;

import io.traveler.travel.diary.dto.input.CreateDiaryInput;
import io.traveler.travel.diary.dto.input.UpdateDiaryInput;
import io.traveler.travel.diary.dto.response.DiaryResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface DiaryService {
    Slice<DiaryResponse> findAll(Pageable pageable);

    DiaryResponse findDiaryById(long id);

    void registerDiary(CreateDiaryInput createDiaryInput);

    void modifyDiary(UpdateDiaryInput input);

    void removeDiary(long id);
}
