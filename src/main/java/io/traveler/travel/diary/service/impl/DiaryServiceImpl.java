package io.traveler.travel.diary.service.impl;

import io.traveler.travel.diary.dto.input.CreateDiaryInput;
import io.traveler.travel.diary.dto.input.UpdateDiaryInput;
import io.traveler.travel.diary.dto.response.DiaryResponse;
import io.traveler.travel.diary.entity.Diary;
import io.traveler.travel.diary.entity.DiaryImage;
import io.traveler.travel.diary.repository.DiaryRepository;
import io.traveler.travel.diary.service.DiaryService;
import io.traveler.travel.image.ImageUploader;
import io.traveler.travel.trip.entity.Trip;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaryServiceImpl implements DiaryService {
    private final DiaryRepository diaryRepository;
    private final ImageUploader imageUploader;

    public DiaryServiceImpl(DiaryRepository diaryRepository, ImageUploader imageUploader) {
        this.diaryRepository = diaryRepository;
        this.imageUploader = imageUploader;
    }

    @Override
    public Slice<DiaryResponse> findAll(Pageable pageable) {
        Slice<Diary> diarySlice = diaryRepository.findByIsDeletedFalse(pageable);
        return diarySlice.map(DiaryResponse::from);
    }

    @Override
    public DiaryResponse findDiaryById(Long id) {
        return diaryRepository.findById(id)
                .map(DiaryResponse::from)
                .orElseThrow();
    }

    @Override
    public void registerDiary(CreateDiaryInput input) {
        List<String> imagesUrl = input.imageBytesList().stream()
                .map(imageUploader::handleUpload)
                .toList();

        List<DiaryImage> images = imagesUrl.stream()
//                프로젝트 내에서 엔티티 생성방법을 통일 시키고 싶으면 빌더 쓰는게 맞는거 같은데 아무리 봐도 이게 더 읽기 쉽다고 느껴짐
//                .map(url -> DiaryImage.builder().url(url).build())
                .map(DiaryImage::new)
                .toList();


        Diary diary = Diary.builder()
                .title(input.title())
                .content(input.content())
                .diaryImages(images)
                .build();

        diaryRepository.save(diary);
    }

    @Override
    public void modifyDiary(UpdateDiaryInput input) {
        List<String> imagesUrl = input.imageBytesList().stream()
                .map(imageUploader::handleUpload)
                .toList();


        Diary.builder()
                .title(input.title())
                .content(input.content())
                .build();

    }
}
