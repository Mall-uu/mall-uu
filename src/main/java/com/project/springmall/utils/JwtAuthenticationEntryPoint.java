package com.project.springmall.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.springmall.exception.ExceptionEnum;
import com.project.springmall.exception.api.ApiException;
import com.project.springmall.exception.api.ApiExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import org.springframework.security.core.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
        HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException e) throws IOException {
        log.error("Responding with unauthorized error. Message - {}", e.getMessage());

        ApiException unAuthorizationException = new ApiException(ExceptionEnum.UNAUTHORIZED);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        String json = new ObjectMapper().writeValueAsString(unAuthorizationException.toApiResponse());
        response.getWriter().write(json);
        response.flushBuffer();

    }
}