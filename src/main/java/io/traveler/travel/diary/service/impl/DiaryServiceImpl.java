package io.traveler.travel.diary.service.impl;

import io.traveler.travel.diary.dto.input.*;
import io.traveler.travel.diary.dto.response.DiaryCommentResponse;
import io.traveler.travel.diary.dto.response.DiaryReplyResponse;
import io.traveler.travel.diary.dto.response.DiaryResponse;
import io.traveler.travel.diary.entity.Diary;
import io.traveler.travel.diary.entity.DiaryComment;
import io.traveler.travel.diary.entity.DiaryImage;
import io.traveler.travel.diary.entity.DiaryReply;
import io.traveler.travel.diary.repository.DiaryCommentRepository;
import io.traveler.travel.diary.repository.DiaryImageRepository;
import io.traveler.travel.diary.repository.DiaryReplyRepository;
import io.traveler.travel.diary.repository.DiaryRepository;
import io.traveler.travel.diary.service.DiaryService;
import io.traveler.travel.image.ImageUploader;
import io.traveler.travel.trip.entity.Trip;
import io.traveler.travel.trip.repository.TripRepository;
import io.traveler.travel.user.entity.User;
import io.traveler.travel.user.repository.UserRepository;
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
    private final DiaryCommentRepository diaryCommentRepository;
    private final DiaryImageRepository diaryImageRepository;
    private final DiaryReplyRepository diaryReplyRepository;
    private final UserRepository userRepository;

    public DiaryServiceImpl(DiaryRepository diaryRepository,
                            TripRepository tripRepository,
                            ImageUploader imageUploader,
                            DiaryCommentRepository diaryCommentRepository,
                            DiaryImageRepository diaryImageRepository,
                            DiaryReplyRepository diaryReplyRepository,
                            UserRepository userRepository) {

        this.diaryRepository = diaryRepository;
        this.tripRepository = tripRepository;
        this.imageUploader = imageUploader;
        this.diaryCommentRepository = diaryCommentRepository;
        this.diaryImageRepository = diaryImageRepository;
        this.diaryReplyRepository = diaryReplyRepository;
        this.userRepository = userRepository;
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
            input.imageBytesList().stream()
                    .map(imageUploader::handleUpload)
                    .map(url -> DiaryImage.builder()
                            .diary(diary)
                            .url(url)
                            .build())
                    .map(diaryImageRepository::save);
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
            input.imageBytesList().stream()
                    .map(imageUploader::handleUpload)
                    .map(url -> DiaryImage.builder()
                            .diary(diary)
                            .url(url)
                            .build())
                    .map(diaryImageRepository::save);
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

    @Override
    public Slice<DiaryCommentResponse> getComments(long diaryId, Pageable pageable) {
//      있는지 검증하기 위해 getReferenceById 대신 실제로 조회
        Diary diary = diaryRepository.findById(diaryId).orElseThrow();

        Slice<DiaryComment> commentSlice = diaryCommentRepository.findByDiary(diary, pageable);
        return commentSlice.map(DiaryCommentResponse::from);
    }

    @Override
    public Slice<DiaryReplyResponse> getReplies(long diaryId, long commentId, Pageable pageable) {

        DiaryComment comment = diaryCommentRepository.findById(commentId).orElseThrow();

        Slice<DiaryReply> replySlice = diaryReplyRepository.findByDiaryComment(comment, pageable);
        return replySlice.map(DiaryReplyResponse::from);
    }

    @Override
    public void createComment(CreateCommentInput input) {
        Diary diary = diaryRepository.findById(input.diaryId())
                .orElseThrow();

        User poster = userRepository.getReferenceById(input.posterId());

        DiaryComment comment = DiaryComment.builder()
                .diary(diary)
                .poster(poster)
                .content(input.content())
                .build();

        diaryCommentRepository.save(comment);
    }

    @Override
    public void createReply(CreateReplyInput input) {

        diaryCommentRepository.findById(input.commentId())
                .orElseThrow();

        User poster = userRepository.getReferenceById(input.posterId());

        DiaryReply reply = DiaryReply.builder()
                .poster(poster)
                .content(input.content())
                .build();

        diaryReplyRepository.save(reply);
    }

    @Override
    public void modifyComment(UpdateCommentInput input) {
        DiaryComment comment = diaryCommentRepository.findById(input.commentId())
                .orElseThrow();

        comment.updateContent(input.content());

    }

    @Override
    public void modifyReply(UpdateReplyInput input) {
        DiaryReply reply = diaryReplyRepository.findById(input.replyId())
                .orElseThrow();

        reply.updateContent(input.content());
    }

    @Override
    public void removeComment(long diaryId, long commentId) {
        Diary diary = diaryRepository.findById(diaryId).orElseThrow();
        DiaryComment comment = diaryCommentRepository.findByDiary(diary).orElseThrow();
        comment.delete();
    }

    @Override
    public void removeReply(long diaryId, long commentId, long replyId) {
        Diary diary = diaryRepository.findById(diaryId).orElseThrow();
        DiaryComment comment = diaryCommentRepository.findByDiary(diary).orElseThrow();
        DiaryReply reply = diaryReplyRepository.findByDiaryComment(comment).orElseThrow();
        reply.delete();
    }




}
