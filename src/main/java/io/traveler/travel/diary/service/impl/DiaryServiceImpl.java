package io.traveler.travel.diary.service.impl;

import io.traveler.travel.diary.dto.input.CreateDiaryInput;
import io.traveler.travel.diary.dto.input.UpdateDiaryInput;
import io.traveler.travel.diary.dto.response.DiaryResponse;
import io.traveler.travel.diary.entity.Diary;
import io.traveler.travel.diary.entity.DiaryImage;
import io.traveler.travel.diary.repository.DiaryImageRepository;
import io.traveler.travel.diary.repository.DiaryRepository;
import io.traveler.travel.diary.service.DiaryService;
import io.traveler.travel.image.ImageUploader;
import io.traveler.travel.trip.entity.Trip;
import io.traveler.travel.trip.repository.TripRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
public class DiaryServiceImpl implements DiaryService {

    private final DiaryRepository diaryRepository;
    private final TripRepository tripRepository;
    private final ImageUploader imageUploader;
    private final DiaryImageRepository diaryImageRepository;

    public DiaryServiceImpl(DiaryRepository diaryRepository,
                            TripRepository tripRepository,
                            ImageUploader imageUploader,
                            DiaryImageRepository diaryImageRepository) {

        this.diaryRepository = diaryRepository;
        this.tripRepository = tripRepository;
        this.imageUploader = imageUploader;
        this.diaryImageRepository = diaryImageRepository;
    }

    @Override
    public Slice<DiaryResponse> getDiaries(Pageable pageable) {
        Slice<Diary> diarySlice = diaryRepository.findByAll(pageable);
        return diarySlice.map(DiaryResponse::from);
    }

    @Override
    public DiaryResponse getDiary(long id) {
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
            input.imageBytesList().stream()
                    .map(imageUploader::handleUpload)
                    .map(url -> DiaryImage.builder()
                            .diary(diary)
                            .url(url)
                            .build())
                    .forEach(diaryImageRepository::save);
        }

        if (input.thumbnail().length != 0) {
            String thumbnailUrl = imageUploader.handleUpload(input.thumbnail());
            diary.updateThumbnailUrl(thumbnailUrl);
        }
        diaryRepository.save(diary);
    }

    @Override
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
            input.imageBytesList().stream()
                    .map(imageUploader::handleUpload)
                    .map(url -> DiaryImage.builder()
                            .diary(diary)
                            .url(url)
                            .build())
                    .forEach(diaryImageRepository::save);
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
        diary.delete();
    }
}
