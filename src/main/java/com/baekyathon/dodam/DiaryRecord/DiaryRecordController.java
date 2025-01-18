package com.baekyathon.dodam.DiaryRecord;


import com.baekyathon.dodam.base.BaseResponse;
import com.baekyathon.dodam.diary.DiaryResponseDTO;
import com.baekyathon.dodam.diary.DiaryService;
import com.baekyathon.dodam.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/record")
@Tag(name = "레코드 기록", description = "record 기록 관련 API")
public class DiaryRecordController {

  private final DiaryRecordService diaryRecordService;

  // 기록 저장
  @Operation(summary = "기록 저장", description = "기록을 저장하는 API")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "기록 저장 성공",
                  content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "400", description = "잘못된 요청: 입력값 오류",
                  content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  })
  @PostMapping("/save")
  public ResponseEntity<BaseResponse<String>> saveDiaryRecord(
          @RequestParam Long babyId,
          @RequestParam String category,
          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
    diaryRecordService.saveDiaryRecord(babyId, category, date);

    // 성공적으로 저장된 경우 응답
    return ResponseEntity.ok(BaseResponse.success("Record saved successfully"));
  }
}









