package com.baekyathon.dodam.DiaryRecord;

import com.baekyathon.dodam.diary.DiaryReqDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diary-records")
public class DiaryRecordController {

    private final DiaryRecordService diaryRecordService;

    @PostMapping
    @Operation(summary = "DiaryRecord 생성", description = "DiaryRecord를 생성합니다.")
    public ResponseEntity<DiaryRecordResDto> createDiaryRecord(
            @RequestBody DiaryRecordReqDto request) {
        DiaryRecordResDto response = diaryRecordService.createDiaryRecord(request);
        return ResponseEntity.ok(response);
    }

}

