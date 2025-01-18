package com.baekyathon.dodam.base;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<BaseResponse<Object>> handleCustomException(CustomException ex) {
        ErrorInterface error = ex.getErrorInterface();
        return ResponseEntity.status(error.getStatus())
                .body(BaseResponse.fail(error));
    }
}
