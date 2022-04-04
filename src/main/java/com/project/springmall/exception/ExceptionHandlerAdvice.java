/*
 * Copyright (c) 2022. Kang PilGyu
 * All rights reserved.
 */

package com.project.springmall.exception;

import com.project.springmall.exception.api.ApiException;
import com.project.springmall.exception.api.ApiExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiExceptionResponse> exceptionHandler(HttpServletRequest request, final ApiException e) {
        return ResponseEntity.status(e.getExceptionEnum().getStatus())
                .body(e.toApiResponse());
    }
}
