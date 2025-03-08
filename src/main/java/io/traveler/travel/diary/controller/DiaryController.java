package io.traveler.travel.diary.controller;

import io.traveler.travel.common.utils.FileUtil;
import io.traveler.travel.diary.dto.input.CreateDiaryInput;
import io.traveler.travel.diary.dto.input.UpdateDiaryInput;
import io.traveler.travel.diary.dto.request.CreateDiaryRequest;
import io.traveler.travel.diary.dto.request.UpdateDiaryRequest;
import io.traveler.travel.diary.dto.response.DiaryResponse;
import io.traveler.travel.diary.service.DiaryService;


import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/diary")
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @GetMapping()
    public Slice<DiaryResponse> getAll(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size) {

        return diaryService.findAll(PageRequest.of(page, size));
    }

    @GetMapping("{id}")
    public DiaryResponse getDiary(@PathVariable long id) {
        return diaryService.findDiaryById(id);
    }

    @PostMapping()
    public void createDiary(@ModelAttribute @Valid CreateDiaryRequest createDiaryRequest) {
        List<byte[]> imageBytesList = createDiaryRequest.images().stream()
                .map(FileUtil::transferImageToBytes)
                .toList();

        byte[] thumbnail = FileUtil.transferImageToBytes(createDiaryRequest.thumbnail());

        CreateDiaryInput input = CreateDiaryInput.from(createDiaryRequest)
                .withImages(imageBytesList)
                .withThumbnail(thumbnail);

        diaryService.registerDiary(input);
    }

    @PutMapping("{id}")
    public void updateDiary(@PathVariable long id,
                            @ModelAttribute UpdateDiaryRequest updateDiaryRequest) {
        List<byte[]> imageBytesList = updateDiaryRequest.images().stream()
                .map(FileUtil::transferImageToBytes)
                .toList();

        UpdateDiaryInput input = UpdateDiaryInput.from(updateDiaryRequest)
                .WithId(id)
                .withImages(imageBytesList);

        diaryService.modifyDiary(input);
    }

    @DeleteMapping
    public void deleteDiary(@PathVariable long id) {
        diaryService.removeDiary(id);
    }



}
