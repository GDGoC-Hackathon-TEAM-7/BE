package com.baekyathon.dodam.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Data
public class BaseResponse<T> {
    HttpStatus status;
    String message;
    T result;

    // 응답 성공
    public static <T> BaseResponse<T> success(T result) {
        return new BaseResponse<>(HttpStatus.OK, "Success", result);
    }

    // 실패한 응답 (예: 데이터가 없거나 요청이 잘못된 경우)
    public static <T> BaseResponse<T> fail(HttpStatus status, String message) {
        return new BaseResponse<>(status, message, null);
    }

    // 서버 오류 응답
    public static <T> BaseResponse<T> error(String message) {
        return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, message, null);
    }
}
