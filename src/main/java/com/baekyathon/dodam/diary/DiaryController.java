package com.baekyathon.dodam.diary;



import com.baekyathon.dodam.DiaryRecord.DiaryRecordDTO;
import com.baekyathon.dodam.base.BaseResponse;
import com.baekyathon.dodam.base.CustomException;
import com.baekyathon.dodam.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.baekyathon.dodam.base.ErrorCode.INVALID_REQUEST;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diary")
@Tag(name = "다이어리", description = "다이어리 관련 API")
public class DiaryController {

  private final DiaryService diaryService;

  // 날짜별 전체 기록 조회
  @Operation(summary = "전체 기록 조회", description = "날짜별로 전체 diaryRecord를 확인할 수 있는 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공",
          content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
              schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = User.class))),
      @ApiResponse(responseCode = "400", description = "잘못된 요청: 입력값 오류",
          content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  })
  @GetMapping("/{id}")
  public ResponseEntity<BaseResponse<DiaryResponseDTO>> getDiaryRecordsByDate(
      @PathVariable Long id,
      @RequestParam LocalDateTime date) {
      // 날짜별로 기록을 조회
      List<DiaryRecordDTO> records = diaryService.getDiaryRecordsByDate(id, String.valueOf(date));

      // 조회한 기록을 DiaryResponseDTO로 변환
      DiaryResponseDTO responseDTO = new DiaryResponseDTO(date, records);

      return ResponseEntity.ok(BaseResponse.success(responseDTO));

  }
  // 사진 첨부


  // 메모 추가



    // 날짜별 전체 사진 조회




}
