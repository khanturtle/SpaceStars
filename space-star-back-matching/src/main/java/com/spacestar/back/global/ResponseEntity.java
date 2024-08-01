package com.spacestar.back.global;

public record ResponseEntity<T>( String message, int code, T result) {
    /**
     * 필요값 : 성공여부, 메시지, 에러코드, 결과값
     */
    // 요청에 성공한 경우 -> return 객체가 필요한 경우
    public ResponseEntity(ResponseSuccess response,T result) {
        this( response.getMessage(), response.getCode(), result);
    }

    // 요청에 성공한 경우 -> return 객체가 필요 없는 경우
    public ResponseEntity(ResponseSuccess response) {
        this( response.getMessage(), response.getCode(), null);
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

}

