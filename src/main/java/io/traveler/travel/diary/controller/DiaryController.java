package io.traveler.travel.diary.controller;

import io.traveler.travel.global.utils.FileUtil;
import io.traveler.travel.diary.dto.input.*;
import io.traveler.travel.diary.dto.request.*;
import io.traveler.travel.diary.dto.response.DiaryCommentResponse;
import io.traveler.travel.diary.dto.response.DiaryReplyResponse;
import io.traveler.travel.diary.dto.response.DiaryResponse;
import io.traveler.travel.diary.service.DiaryCommentService;
import io.traveler.travel.diary.service.DiaryReplyService;
import io.traveler.travel.diary.service.DiaryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/diary")
public class DiaryController {
    private final DiaryService diaryService;
    private final DiaryCommentService diaryCommentService;
    private final DiaryReplyService diaryReplyService;

    public DiaryController(DiaryService diaryService, DiaryCommentService diaryCommentService, DiaryReplyService diaryReplyService) {
        this.diaryService = diaryService;
        this.diaryCommentService = diaryCommentService;
        this.diaryReplyService = diaryReplyService;
    }


    @GetMapping()
    public Slice<DiaryResponse> getAll(@RequestParam(defaultValue = "0") @Min(0) int page,
                                       @RequestParam(defaultValue = "10") @Min(1) @Max(20) int size) {
        return diaryService.getDiaries(PageRequest.of(page, size));
    }

    @GetMapping("{diaryId}")
    public DiaryResponse getDiary(@PathVariable long diaryId) {
        return diaryService.getDiary(diaryId);
    }

    @PostMapping()
    public void createDiary(@ModelAttribute @Valid CreateDiaryRequest createDiaryRequest,
                            @AuthenticationPrincipal UserDetails user) {

        List<byte[]> imageBytesList = createDiaryRequest.images().stream()
                .map(FileUtil::transferImageToBytes)
                .toList();

        byte[] thumbnail = FileUtil.transferImageToBytes(createDiaryRequest.thumbnail());

        CreateDiaryInput input = CreateDiaryInput.of(createDiaryRequest, imageBytesList, thumbnail, user.getUsername());
        diaryService.registerDiary(input);
    }

    @PutMapping("{diaryId}")
    public void updateDiary(@PathVariable long diaryId,
                            @ModelAttribute UpdateDiaryRequest updateDiaryRequest,
                            @AuthenticationPrincipal UserDetails user) {
        List<byte[]> imageBytesList = updateDiaryRequest.images().stream()
                .map(FileUtil::transferImageToBytes)
                .toList();
        byte[] thumbnail = FileUtil.transferImageToBytes(updateDiaryRequest.thumbnail());

        UpdateDiaryInput input = UpdateDiaryInput.of(updateDiaryRequest, diaryId, imageBytesList, thumbnail, user.getUsername());
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
        return diaryCommentService.getComments(diaryId, PageRequest.of(page, size));
    }

    @PostMapping("{diaryId}/comment")
    public void createComment(@PathVariable long diaryId,
                              @RequestBody @Valid CreateCommentRequest createCommentRequest) {
        CreateCommentInput input = CreateCommentInput.from(createCommentRequest).withDiaryId(diaryId);
        diaryCommentService.createComment(input);
    }

    @PutMapping("{diaryId}/comment/{commentId}")
    public void updateComment(@PathVariable long diaryId,
                              @PathVariable long commentId,
                              @RequestBody @Valid UpdateCommentRequest updateCommentRequest,
                              @AuthenticationPrincipal UserDetails user) {

        UpdateCommentInput input = UpdateCommentInput.of(updateCommentRequest, diaryId, commentId, user.getUsername());
        diaryCommentService.modifyComment(input);
    }

    @DeleteMapping("{diaryId}/comment/{commentId}")
    public void removeComment(@PathVariable long diaryId,
                              @PathVariable long commentId) {
        diaryCommentService.removeComment(diaryId, commentId);
    }


    @GetMapping("{diaryId}/comment/{commentId}/replies")
    public Slice<DiaryReplyResponse> getReplies(@PathVariable long diaryId,
                                                @PathVariable long commentId,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        return diaryReplyService.getReplies(diaryId, commentId, PageRequest.of(page, size));
    }

    @PostMapping("{diaryId}/comment/{commentId}/reply")
    public void createReply(@PathVariable long diaryId,
                            @PathVariable long commentId,
                            @RequestBody @Valid CreateReplyRequest createReplyRequest,
                            @AuthenticationPrincipal UserDetails user) {

        CreateReplyInput input = CreateReplyInput.of(createReplyRequest, diaryId, commentId, user.getUsername());
        diaryReplyService.createReply(input);
    }

    @PutMapping("{diaryId}/comment/{commentId}/reply/{replyId}")
    public void updateReply(@PathVariable long diaryId,
                            @PathVariable long commentId,
                            @PathVariable long replyId,
                            @RequestBody @Valid UpdateReplyRequest updateReplyRequest,
                            @AuthenticationPrincipal UserDetails user) {

        UpdateReplyInput input = UpdateReplyInput.of(updateReplyRequest, diaryId, commentId, replyId, user.getUsername());
        diaryReplyService.modifyReply(input);
    }

    @DeleteMapping("{diaryId}/comment/{commentId}/reply/{replyId}")
    public void removeReply(@PathVariable long diaryId,
                            @PathVariable long commentId,
                            @PathVariable long replyId) {
        diaryReplyService.removeReply(diaryId, commentId, replyId);
    }
}
