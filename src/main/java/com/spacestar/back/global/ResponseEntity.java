package com.spacestar.back.global;

import io.swagger.v3.oas.models.headers.Header;
import org.springframework.lang.Nullable;

public record ResponseEntity<T>(String message, int code, T result) {
    /**
     * 필요값 : 성공여부, 메시지, 에러코드, 결과값
     */
    // 요청에 성공한 경우 -> return 객체가 필요한 경우
    public ResponseEntity(ResponseSuccess success, T result) {
        this( success.getMessage(), ResponseStatus.SUCCESS.getCode(), result);
    }

    // 요청에 성공한 경우 -> return 객체가 필요 없는 경우
    public ResponseEntity(ResponseSuccess success) {
        this( success.getMessage(), ResponseStatus.SUCCESS.getCode(), null);
    }

    // 요청 실패한 경우
    public ResponseEntity(ResponseStatus status) {
        this( status.getMessage(), status.getCode(), null);
    }

    // 요청 실패한 경우 @RuntimeError
    public ResponseEntity(ResponseStatus status, String message) {
        this( message, status.getCode(), null);
    }

    //요청에 실패한 경우 @Vaild annotantion error
    public ResponseEntity(Exception e, String message) {
        this( message, 3000, null);
    }


    // 요청 성공 시, body를 포함한 ResponseEntity 객체를 반환
    public static <T> org.springframework.http.ResponseEntity<T> ok(@Nullable T body) {
        return org.springframework.http.ResponseEntity.ok().body(body);
    }

}

