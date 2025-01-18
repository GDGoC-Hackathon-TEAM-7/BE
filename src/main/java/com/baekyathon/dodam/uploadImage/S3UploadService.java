package com.baekyathon.dodam.uploadImage;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class S3UploadService {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucketName}")
    private String bucketName;

    /**
     * 업로드 처리
     */
    public String upload(@RequestBody MultipartFile image) {
        try {
            // S3에 업로드할 파일명 생성
            String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();

            // S3에 파일 업로드
            amazonS3.putObject(new PutObjectRequest(bucketName, fileName, image.getInputStream(), null));

            // 업로드된 파일 URL 반환
            return amazonS3.getUrl(bucketName, fileName).toString();
        } catch (Exception e) {
            throw new RuntimeException("파일 업로드에 실패했습니다: " + e.getMessage());
        }
    }
}
