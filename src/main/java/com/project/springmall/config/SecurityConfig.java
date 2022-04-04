/*
 * Copyright (c) 2022. Kang PilGyu
 * All rights reserved.
 */

package com.project.springmall.config;

import com.project.springmall.utils.JwtAuthenticationEntryPoint;
import com.project.springmall.utils.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private final JwtAuthenticationEntryPoint unauthorizedHandler;

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        //web.ignoring().antMatchers("/api/auth/**");
//    }

    /**
     * 스프링시큐리티의 각종 설정은 HttpSecurity로 대부분 하게 됩니다.
     *
     * 리소스(URL) 접근 권한 설정
     * 인증 전체 흐름에 필요한 Login, Logout 페이지 인증완료 후 페이지 인증 실패 시 이동페이지 등등 설정
     * 인증 로직을 커스텀하기위한 커스텀 필터 설정
     * 기타 csrf, 강제 https 호출 등등 거의 모든 스프링시큐리티의 설정
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // WebSecurity에 접근 허용 설정을 해버리면 이 설정이 적용되지 않는다.
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                // JWT => STATELESS
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers( "/api/auth/**")
                .permitAll()
                .antMatchers("/**")
                .authenticated();
    }
}
