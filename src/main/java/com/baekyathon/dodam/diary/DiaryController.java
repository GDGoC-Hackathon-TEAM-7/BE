package com.baekyathon.dodam.diary;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDate;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diary")
@Tag(name = "육아 다이어리", description = "육아 다이어리 관련 API")
public class DiaryController {

  private final DiaryService diaryService;

    @PostMapping
    @Operation(summary = "육아 다이어리 생성", description = "아이의 특정 날짜에 대한 Diary를 생성합니다.")
    public ResponseEntity<DiaryResDto> createDiary(
            @RequestBody DiaryReqDto request) {
        DiaryResDto response = diaryService.createDiary(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findDate")
    @Operation(summary = "날짜를 기반으로 DairyId를 알아낸다.", description = "날짜를 기반으로 diaryId 를 알아낸다.")
    public Long findDiaryIdByDate(@RequestParam Long babyId, @RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date); // 날짜 변환
        return diaryService.findDiaryIdByBabyIdAndDate(babyId, localDate);
    }

    @GetMapping("/records")
    @Operation(summary = "Diary와 DiaryRecord 조회", description = "특정 아이와 날짜를 기준으로 Diary와 모든 DiaryRecord를 조회합니다.")
    public ResponseEntity<DiaryResDto> getDiaryAndRecords(
            @RequestParam Long babyId,
            @RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        DiaryResDto response = diaryService.getDiaryAndRecordsByBabyIdAndDate(babyId, localDate);
        return ResponseEntity.ok(response);
    }

}
