package com.baekyathon.dodam.DiaryRecord;


import com.baekyathon.dodam.base.BaseResponse;
import com.baekyathon.dodam.diary.DiaryResponseDTO;
import com.baekyathon.dodam.diary.DiaryService;
import com.baekyathon.dodam.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
@Tag(name = "회원", description = "회원 관련 API")
public class DiaryRecordController {

  private final DiaryRecordService diaryRecordService;

  // 기록 저장
  @Operation(summary = "기록 저장", description = "기록을 저장하는 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "기록 저장 성공",
          content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
              schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = DiaryResponseDTO.class))),
      @ApiResponse(responseCode = "400", description = "잘못된 요청: 입력값 오류",
          content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  })
  @PostMapping("/save")
  public ResponseEntity<Void> saveDiaryRecord(
      @RequestParam Long babyId,
      @RequestParam String category,
      @RequestParam String date) {
    try {
      // 날짜, 카테고리, babyId를 기반으로 기록 저장
      diaryRecordService.saveDiaryRecord(babyId, category, date);

      // 성공적으로 저장된 경우 HTTP 200 OK 응답만 반환
      return ResponseEntity.ok().build(); // 응답 본문 없이 HTTP 200 OK 반환

    } catch (Exception e) {
      // 오류 발생 시 실패 응답 반환
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

}









