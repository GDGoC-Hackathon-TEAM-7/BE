package com.baekyathon.dodam.base;

import org.springframework.http.HttpStatus;

public enum ErrorCode implements ErrorInterface {
    INVALID_REQUEST("Invalid request", HttpStatus.BAD_REQUEST),
    SERVER_ERROR("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
    BABY_NOT_FOUND("아이를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND("유저를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
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
