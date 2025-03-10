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
import io.traveler.travel.trip.repository.TripRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DiaryServiceImpl implements DiaryService {
    private final DiaryRepository diaryRepository;
    private final TripRepository tripRepository;
    private final ImageUploader imageUploader;

    public DiaryServiceImpl(DiaryRepository diaryRepository, TripRepository tripRepository, ImageUploader imageUploader) {
        this.diaryRepository = diaryRepository;
        this.tripRepository = tripRepository;
        this.imageUploader = imageUploader;
    }

    @Override
    public Slice<DiaryResponse> findAll(Pageable pageable) {
        Slice<Diary> diarySlice = diaryRepository.findByIsDeletedFalse(pageable);
        return diarySlice.map(DiaryResponse::from);
    }

    @Override
    public DiaryResponse findDiaryById(long id) {
        return diaryRepository.findById(id)
                .map(DiaryResponse::from)
                .orElseThrow();
    }

    @Override
    public void registerDiary(CreateDiaryInput input) {

        Trip trip = tripRepository.getReferenceById(input.tripId());

        Diary diary = Diary.builder()
                .trip(trip)
                .title(input.title())
                .content(input.content())
                .build();

        if (!input.imageBytesList().isEmpty()) {
            List<DiaryImage> images = input.imageBytesList().stream()
                    .map(imageUploader::handleUpload)
                    .map(DiaryImage::new)
                    .toList();
            diary.updateDiaryImages(images);
        }

        if (input.thumbnail().length != 0) {
            String thumbnailUrl = imageUploader.handleUpload(input.thumbnail());
            diary.updateThumbnailUrl(thumbnailUrl);
        }

        diaryRepository.save(diary);


    }

    @Override
    @Transactional
    public void modifyDiary(UpdateDiaryInput input) {
        Diary diary = diaryRepository.findById(input.id())
                .orElseThrow();

        if (input.title() != null) {
            diary.updateTitle(input.title());
        }

        if (input.content() != null) {
            diary.updateContent(input.content());
        }

        if (!input.imageBytesList().isEmpty()) {
            List<DiaryImage> images = input.imageBytesList().stream()
                    .map(imageUploader::handleUpload)
                    .map(DiaryImage::new)
                    .toList();
            diary.updateDiaryImages(images);
        }

        if (input.thumbnail().length != 0) {
            String thumbnailUrl = imageUploader.handleUpload(input.thumbnail());
            diary.updateThumbnailUrl(thumbnailUrl);
        }

    }

    @Override
    public void removeDiary(long id) {
        Diary diary = diaryRepository.findById(id)
                .orElseThrow();
//      소프트 딜리트
        diary.delete();
    }
}
