package com.baekyathon.dodam.baby;

import com.baekyathon.dodam.base.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/baby")
@Tag(name = "아이", description = "아이 관련 API")
public class BabyController {

    private final BabyService babyService;

    @Operation(summary = "아이 등록", description = "폼을 입력하여 아이를 등록할 수 있는 API")
    @PostMapping
    public ResponseEntity<BaseResponse<BabyInfoDto>> addBaby(@RequestBody BabyReqDto babyReqDto) {
        BabyInfoDto res = babyService.addBaby(babyReqDto);
        return ResponseEntity.ok(BaseResponse.success(res));
    }

    // 조회
    @Operation(summary = "아이 정보 조회", description = "아이 정보를 조회하는 API")
    @GetMapping("/{babyId}")
    public ResponseEntity<BaseResponse<BabyInfoDto>> findBabyById(@PathVariable Long babyId) {
        BabyInfoDto res = babyService.findBabyById(babyId);
        return ResponseEntity.ok(BaseResponse.success(res));
    }

    // 수정
    @Operation(summary = "아이 정보 수정", description = "아이 정보를 수정하는 API")
    @PatchMapping("/{babyId}")
    public ResponseEntity<BaseResponse<BabyInfoDto>> updateBabyById(@PathVariable Long babyId, @RequestBody BabyInfoDto babyInfoDto) {
        BabyInfoDto res = babyService.update(babyId, babyInfoDto);
        return ResponseEntity.ok(BaseResponse.success(res));
    }

    // 삭제
    @Operation(summary = "아이 삭제", description = "아이 정보를 삭제하는 API")
    @DeleteMapping("/{babyId}")
    public void deleteBabyById(@PathVariable Long babyId) {
        babyService.delete(babyId);
    }

}
