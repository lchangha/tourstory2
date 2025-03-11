package io.traveler.travel.diary.service;

import io.traveler.travel.diary.dto.input.CreateDiaryInput;
import io.traveler.travel.diary.dto.input.UpdateDiaryInput;
import io.traveler.travel.diary.dto.response.DiaryResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface DiaryService {
    Slice<DiaryResponse> getDiaries(Pageable pageable);

    DiaryResponse getDiary(long id);

    void registerDiary(CreateDiaryInput input);

    void modifyDiary(UpdateDiaryInput input);

    void removeDiary(long id);
}
