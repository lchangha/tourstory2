package io.traveler.travel.diary.service;

import io.traveler.travel.diary.dto.input.*;
import io.traveler.travel.diary.dto.response.DiaryCommentResponse;
import io.traveler.travel.diary.dto.response.DiaryReplyResponse;
import io.traveler.travel.diary.dto.response.DiaryResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface DiaryService {
    Slice<DiaryResponse> findAll(Pageable pageable);

    DiaryResponse findDiaryById(long id);

    void registerDiary(CreateDiaryInput createDiaryInput);

    void modifyDiary(UpdateDiaryInput input);

    void removeDiary(long id);

    Slice<DiaryCommentResponse> getComments(long diaryId, Pageable pageable);

    Slice<DiaryReplyResponse> getReplies(long diaryId, long commentId, Pageable pageable);

    void createReply(CreateReplyInput input);

    void modifyComment(UpdateCommentInput input);

    void modifyReply(UpdateReplyInput input);

    void createComment(CreateCommentInput intput);

    void removeReply(long diaryId, long commentId, long replyId);

    void removeComment(long diaryId, long commentId);
}
