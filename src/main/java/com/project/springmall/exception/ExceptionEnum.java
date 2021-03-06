/*
 * Copyright (c) 2022. Kang PilGyu
 * All rights reserved.
 */

package com.project.springmall.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ExceptionEnum {
    // Sign in
    SIGN_IN_USER_NOT_FOUND(HttpStatus.UNAUTHORIZED, "SG0001", "유저를 찾을 수 없습니다."),
    SIGN_IN_MISS_PASSWORD(HttpStatus.UNAUTHORIZED, "SG0002", "비밀번호가 틀렸습니다."),
    SIGN_IN_NOT_ACTIVE(HttpStatus.UNAUTHORIZED, "SG0003", "이메일 인증을 진행해주세요."),
    EXPIRED_EMAIL_TOKEN(HttpStatus.UNAUTHORIZED, "SG0004", "토큰이 만료되었습니다."),
    MISMATCH_EMAIL_TOKEN(HttpStatus.UNAUTHORIZED, "SG0005", "토큰 인증에 실패하였습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,  "U0001", "???");


    private final HttpStatus status;
    private final String code;
    private String message;

    ExceptionEnum(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    ExceptionEnum(HttpStatus status, String code) {
        this.status = status;
        this.code = code;
    }
}
