package com.baekyathon.dodam.diary;

import com.baekyathon.dodam.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDate;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diary")
@Tag(name = "다이어리", description = "다이어리 관련 API")
public class DiaryController {

  private final DiaryService diaryService;

    @GetMapping("/find-by-date")
    @Operation(summary = "날짜를 기반으로 diaryId를 알아낸다.", description = "날짜를 기반으로 diaryId 를 알아낸다.")
    public Long findDiaryIdByDate(@RequestParam Long babyId, @RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date); // 날짜 변환
        return diaryService.findDiaryIdByBabyIdAndDate(babyId, localDate);
    }

    @PostMapping
    @Operation(summary = "Diary 생성", description = "아이의 특정 날짜에 대한 Diary를 생성합니다.")
    public ResponseEntity<DiaryResDto> createDiary(
            @RequestBody DiaryReqDto request) {
        DiaryResDto response = diaryService.createDiary(request);
        return ResponseEntity.ok(response);
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
