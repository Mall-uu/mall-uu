/*
 * Copyright (c) 2022. Kang PilGyu
 * All rights reserved.
 */

package com.project.springmall.exception.api;

import com.project.springmall.exception.ExceptionEnum;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
    private final ExceptionEnum exceptionEnum;

    public ApiException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.exceptionEnum = exceptionEnum;
    }

    public ApiExceptionResponse toApiResponse() {
        return ApiExceptionResponse.builder()
                .errorCode(exceptionEnum.getCode())
                .errorMessage(exceptionEnum.getMessage())
                .build();
    }
}
