package io.traveler.travel.diary.service;

import io.traveler.travel.diary.dto.input.CreateReplyInput;
import io.traveler.travel.diary.dto.input.UpdateReplyInput;
import io.traveler.travel.diary.dto.response.DiaryReplyResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface DiaryReplyService {
    Slice<DiaryReplyResponse> getReplies(long diaryId, long commentId, Pageable pageable);

    void createReply(CreateReplyInput input);

    void modifyReply(UpdateReplyInput input);

    void removeReply(long diaryId, long commentId, long replyId);
}
