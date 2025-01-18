package com.baekyathon.dodam.controller;

import com.baekyathon.dodam.base.BaseResponse;
import com.baekyathon.dodam.dto.baby.BabyInfoDto;
import com.baekyathon.dodam.service.BabyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/baby")
@Tag(name = "아이", description = "아이 관련 API")
public class BabyController {

    private final BabyService babyService;

    // 추가
    @PostMapping
    public ResponseEntity<BaseResponse<BabyInfoDto>> addBaby(@RequestBody BabyInfoDto babyInfoDto) {
        BabyInfoDto res = babyService.addBaby(babyInfoDto);
        return ResponseEntity.ok(BaseResponse.success(res));
    }

    // 조회
    @GetMapping("/{babyId}")
    public ResponseEntity<BaseResponse<BabyInfoDto>> findBabyById(@PathVariable Long babyId) {
        BabyInfoDto res = babyService.findBabyById(babyId);
        return ResponseEntity.ok(BaseResponse.success(res));
    }

    // 수정


    // 삭제



}
