package io.traveler.travel.diary.controller;

import io.traveler.travel.common.utils.FileUtil;
import io.traveler.travel.diary.dto.input.*;
import io.traveler.travel.diary.dto.request.*;
import io.traveler.travel.diary.dto.response.DiaryCommentResponse;
import io.traveler.travel.diary.dto.response.DiaryReplyResponse;
import io.traveler.travel.diary.dto.response.DiaryResponse;
import io.traveler.travel.diary.service.DiaryService;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/diary")
public class DiaryController {
    private final DiaryService diaryService;
    private final long posterId = 10;
    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @GetMapping()
    public Slice<DiaryResponse> getAll(@RequestParam(defaultValue = "0") @Min(0) int page,
                                       @RequestParam(defaultValue = "10") @Min(1) @Max(20) int size) {

        return diaryService.findAll(PageRequest.of(page, size));
    }

    @GetMapping("{diaryId}")
    public DiaryResponse getDiary(@PathVariable long diaryId) {
        return diaryService.findDiaryById(diaryId);
    }

    @PostMapping()
    public void createDiary(@ModelAttribute @Valid CreateDiaryRequest createDiaryRequest) {

        List<byte[]> imageBytesList = createDiaryRequest.images().stream()
                .map(FileUtil::transferImageToBytes)
                .toList();

        byte[] thumbnail = FileUtil.transferImageToBytes(createDiaryRequest.thumbnail());

//        굳이 이렇게까지?
//        CreateDiaryInput input = CreateDiaryInput.from(createDiaryRequest)
//                .withImages(imageBytesList)
//                .withThumbnail(thumbnail);
        CreateDiaryInput input = CreateDiaryInput.of(createDiaryRequest, imageBytesList, thumbnail, posterId);

        diaryService.registerDiary(input);
    }

    @PutMapping("{diaryId}")
    public void updateDiary(@PathVariable long diaryId,
                            @ModelAttribute UpdateDiaryRequest updateDiaryRequest) {
        List<byte[]> imageBytesList = updateDiaryRequest.images().stream()
                .map(FileUtil::transferImageToBytes)
                .toList();

        byte[] thumbnail = FileUtil.transferImageToBytes(updateDiaryRequest.thumbnail());
//        UpdateDiaryInput input = UpdateDiaryInput.from(updateDiaryRequest)
//                .withId(diaryId)
//                .withImages(imageBytesList);

        UpdateDiaryInput input = UpdateDiaryInput.of(updateDiaryRequest, diaryId, imageBytesList, thumbnail, posterId);

        diaryService.modifyDiary(input);
    }

    @DeleteMapping("{diaryId}")
    public void deleteDiary(@PathVariable long diaryId) {
        diaryService.removeDiary(diaryId);
    }

    @GetMapping("{diaryId}/comments")
    public Slice<DiaryCommentResponse> getComments(@PathVariable long diaryId,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size) {

        return diaryService.getComments(diaryId, PageRequest.of(page, size));
    }

    @GetMapping("{diaryId}/comment/{commentId}/replies")
    public Slice<DiaryReplyResponse> getReplies(@PathVariable long diaryId,
                                                @PathVariable long commentId,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {

        return diaryService.getReplies(diaryId, commentId, PageRequest.of(page, size));
    }

    @PostMapping("{diaryId}/comment")
    public void createComment(@PathVariable long diaryId,
                              @RequestBody @Valid CreateCommentRequest createCommentRequest) {
        CreateCommentInput intput = CreateCommentInput.from(createCommentRequest).withDiaryId(diaryId);
        diaryService.createComment(intput);
    }

    @PostMapping("{diaryId}/comment/{commentId}/reply")
    public void createReply(@PathVariable long diaryId,
                            @PathVariable long commentId,
                            @RequestBody @Valid CreateReplyRequest createReplyRequest) {

        CreateReplyInput input = CreateReplyInput.of(createReplyRequest, diaryId, commentId, posterId);
        diaryService.createReply(input);
    }


    @PutMapping("{diaryId}/comment/{commentId}")
    public void updateComment(@PathVariable long diaryId,
                              @PathVariable long commentId,
                              @RequestBody @Valid UpdateCommentRequest updateCommentRequest) {
        UpdateCommentInput input = UpdateCommentInput.of(updateCommentRequest, diaryId, commentId, posterId);
        diaryService.modifyComment(input);
    }

    @PutMapping("{diaryId}/comment/{commentId}/reply/{replyId}")
    public void updateReply(@PathVariable long diaryId,
                            @PathVariable long commentId,
                            @PathVariable long replyId,
                            @RequestBody @Valid UpdateReplyRequest updateReplyRequest) {

        UpdateReplyInput input = UpdateReplyInput.of(updateReplyRequest, diaryId, commentId, replyId, posterId);
        diaryService.modifyReply(input);
    }

    @DeleteMapping("{diaryId}/comment/{commentId}")
    public void removeComment(@PathVariable long diaryId,
                              @PathVariable long commentId
    ) {
        diaryService.removeComment(diaryId, commentId);
    }

    @DeleteMapping("{diaryId}/comment/{commentId}/reply/{replyId}")
    public void removeReply(@PathVariable long diaryId,
                            @PathVariable long commentId,
                            @PathVariable long replyId) {
        diaryService.removeReply(diaryId, commentId, replyId);
    }
}
