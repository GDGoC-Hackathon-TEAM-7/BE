package com.baekyathon.dodam.base;

import org.springframework.http.HttpStatus;

public enum ErrorCode implements ErrorInterface {
    INVALID_REQUEST("Invalid request", HttpStatus.BAD_REQUEST),
    SERVER_ERROR("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
    BABY_NOT_FOUND("아이를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND("유저를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    DIARY_NOT_FOUND("다이어리를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    EMPTY_FILE_EXCEPTION("File is empty", HttpStatus.BAD_REQUEST),
    INVALID_FILE_EXTENSION("Invalid file extension", HttpStatus.BAD_REQUEST),
    NO_FILE_EXTENSION("File extension is missing", HttpStatus.BAD_REQUEST),
    IO_EXCEPTION_ON_IMAGE_UPLOAD("IO error occurred during image upload", HttpStatus.INTERNAL_SERVER_ERROR),
    PUT_OBJECT_EXCEPTION("Failed to upload object to S3", HttpStatus.INTERNAL_SERVER_ERROR);


    private final String message;
    private final HttpStatus status;

    ErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }
}
