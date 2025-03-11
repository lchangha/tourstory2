package io.traveler.travel.diary.service.impl;

import io.traveler.travel.diary.dto.input.CreateCommentInput;
import io.traveler.travel.diary.dto.input.UpdateCommentInput;
import io.traveler.travel.diary.dto.response.DiaryCommentResponse;
import io.traveler.travel.diary.entity.Diary;
import io.traveler.travel.diary.entity.DiaryComment;
import io.traveler.travel.diary.repository.DiaryCommentRepository;
import io.traveler.travel.diary.repository.DiaryRepository;
import io.traveler.travel.diary.service.DiaryCommentService;
import io.traveler.travel.user.entity.User;
import io.traveler.travel.user.repository.UserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
public class DiaryCommentServiceImpl implements DiaryCommentService {

    private final DiaryRepository diaryRepository;
    private final DiaryCommentRepository diaryCommentRepository;
    private final UserRepository userRepository;

    public DiaryCommentServiceImpl(DiaryRepository diaryRepository,
                                   DiaryCommentRepository diaryCommentRepository,
                                   UserRepository userRepository) {
        this.diaryRepository = diaryRepository;
        this.diaryCommentRepository = diaryCommentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Slice<DiaryCommentResponse> getComments(long diaryId, Pageable pageable) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow();
        Slice<DiaryComment> commentSlice = diaryCommentRepository.findByDiary(diary, pageable);
        return commentSlice.map(DiaryCommentResponse::from);
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
    public void modifyComment(UpdateCommentInput input) {
        DiaryComment comment = diaryCommentRepository.findById(input.commentId())
                .orElseThrow();
        comment.updateContent(input.content());
    }

    @Override
    public void removeComment(long diaryId, long commentId) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow();
        DiaryComment comment = diaryCommentRepository.findByIdAndDiary(commentId, diary)
                .orElseThrow();
        comment.delete();
    }
}
