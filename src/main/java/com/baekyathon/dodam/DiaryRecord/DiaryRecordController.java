package com.baekyathon.dodam.DiaryRecord;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diaryRecords")
@Tag(name = "육아다이어리 기록", description = "육아다이어리 스탬프 API")
public class DiaryRecordController {

    private final DiaryRecordService diaryRecordService;

    @PostMapping
    @Operation(summary = "육아 다이어리 기록하기", description = "육아 다이어리를 기록합니다")
    public ResponseEntity<DiaryRecordResDto> createDiaryRecord(
            @RequestBody DiaryRecordReqDto request) {
        DiaryRecordResDto response = diaryRecordService.createDiaryRecord(request);
        return ResponseEntity.ok(response);
    }

}

