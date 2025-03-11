package io.traveler.travel.diary.service;

import io.traveler.travel.diary.dto.input.CreateCommentInput;
import io.traveler.travel.diary.dto.input.UpdateCommentInput;
import io.traveler.travel.diary.dto.response.DiaryCommentResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface DiaryCommentService {
    Slice<DiaryCommentResponse> getComments(long diaryId, Pageable pageable);

    void createComment(CreateCommentInput input);

    void modifyComment(UpdateCommentInput input);

    void removeComment(long diaryId, long commentId);
}
