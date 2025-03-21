package io.traveler.travel.diary.service.impl;

import io.traveler.travel.diary.dto.input.CreateReplyInput;
import io.traveler.travel.diary.dto.input.UpdateReplyInput;
import io.traveler.travel.diary.dto.response.DiaryReplyResponse;
import io.traveler.travel.diary.entity.DiaryComment;
import io.traveler.travel.diary.entity.DiaryReply;
import io.traveler.travel.diary.repository.DiaryCommentRepository;
import io.traveler.travel.diary.repository.DiaryReplyRepository;
import io.traveler.travel.diary.repository.DiaryRepository;
import io.traveler.travel.diary.service.DiaryReplyService;
import io.traveler.travel.user.entity.User;
import io.traveler.travel.user.repository.UserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
public class DiaryReplyServiceImpl implements DiaryReplyService {

    private final DiaryRepository diaryRepository;
    private final DiaryCommentRepository diaryCommentRepository;
    private final DiaryReplyRepository diaryReplyRepository;
    private final UserRepository userRepository;

    public DiaryReplyServiceImpl(DiaryRepository diaryRepository,
                                 DiaryCommentRepository diaryCommentRepository,
                                 DiaryReplyRepository diaryReplyRepository,
                                 UserRepository userRepository) {
        this.diaryRepository = diaryRepository;
        this.diaryCommentRepository = diaryCommentRepository;
        this.diaryReplyRepository = diaryReplyRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Slice<DiaryReplyResponse> getReplies(long diaryId, long commentId, Pageable pageable) {
        diaryRepository.findById(diaryId)
                .orElseThrow();
        DiaryComment comment = diaryCommentRepository.findById(commentId)
                .orElseThrow();
        Slice<DiaryReply> replySlice = diaryReplyRepository.findByDiaryComment(comment, pageable);
        return replySlice.map(DiaryReplyResponse::from);
    }

    @Override
    public void createReply(CreateReplyInput input) {
        diaryCommentRepository.findById(input.commentId())
                .orElseThrow();
        User poster = userRepository.getReferenceById(input.email());
        DiaryReply reply = DiaryReply.builder()
                .poster(poster)
                .content(input.content())
                .build();
        diaryReplyRepository.save(reply);
    }

    @Override
    public void modifyReply(UpdateReplyInput input) {
        DiaryReply reply = diaryReplyRepository.findById(input.replyId())
                .orElseThrow();
        reply.updateContent(input.content());
    }

    @Override
    public void removeReply(long diaryId, long commentId, long replyId) {

        diaryRepository.findById(diaryId).orElseThrow();
        DiaryComment comment = diaryCommentRepository.findById(commentId).orElseThrow();
        DiaryReply reply = diaryReplyRepository.findByIdAndDiaryComment(replyId, comment)
                .orElseThrow();

        reply.delete();
    }
}
