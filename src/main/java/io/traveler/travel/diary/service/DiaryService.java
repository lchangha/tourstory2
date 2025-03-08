package io.traveler.travel.diary.service;

import io.traveler.travel.diary.dto.input.*;
import io.traveler.travel.diary.dto.response.CommentResponse;
import io.traveler.travel.diary.dto.response.DiaryResponse;
import io.traveler.travel.diary.dto.response.ReplyResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface DiaryService {
    Slice<DiaryResponse> findAll(Pageable pageable);

    DiaryResponse findDiaryById(long id);

    void registerDiary(CreateDiaryInput createDiaryInput);

    void modifyDiary(UpdateDiaryInput input);

    void removeDiary(long id);

    Slice<CommentResponse> getComments(long diaryId, PageRequest of);

    Slice<ReplyResponse> getReplies(long diaryId, long commentId, PageRequest of);

    void createReply(CreateReplyInput input);

    void modifyComment(UpdateCommentInput input);

    void modifyReply(UpdateReplyInput input);
}
