package com.baekyathon.dodam.uploadImage;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/upload")
@Tag(name = "이미지 등록", description = "이미지 등록 관련 API")
public class S3UploadController {

    private final S3UploadService s3UploadService;

    // 이미지 업로드 엔드포인트
    @PostMapping("/image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        // 이미지 파일 업로드 처리
        try {
            String imageUrl = s3UploadService.upload(file);
            return ResponseEntity.ok(imageUrl); // 업로드한 이미지 URL 반환
        } catch (AmazonS3Exception e) {
            // 파일 업로드 오류 처리
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST) // 적절한 상태 코드 반환
                    .body("Image upload failed: " + e.getMessage());
        } catch (Exception e) {
            // 기타 예외 처리
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR) // 서버 내부 오류
                    .body("Unexpected error occurred: " + e.getMessage());
        }
    }
}
