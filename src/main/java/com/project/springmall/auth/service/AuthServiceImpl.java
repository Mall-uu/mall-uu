/*
 * Copyright (c) 2022. Kang PilGyu
 * All rights reserved.
 */

package com.project.springmall.auth.service;

import com.project.springmall.auth.domain.RegistrationEmailToken;
import com.project.springmall.auth.domain.RegistrationEmailTokenRepository;
import com.project.springmall.common.BaseService;
import com.project.springmall.exception.ExceptionEnum;
import com.project.springmall.exception.api.ApiException;
import com.project.springmall.notification.email.EmailService;
import com.project.springmall.user.service.UserDTO;
import com.project.springmall.user.domain.User;
import com.project.springmall.user.domain.UserRepository;
import com.project.springmall.utils.JwtTokenProvider;
import com.project.springmall.utils.UserAuthentication;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Base64Encoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl extends BaseService implements AuthService {
    private final UserRepository userRepository;
    private final RegistrationEmailTokenRepository registrationEmailTokenRepository;
    private final EmailService emailService;

    @Override
    @Transactional
    public User register(UserDTO.LocalSignUpReq localSignUpReq) throws ApiException {
        // 1. Validation Checking
        boolean isExist = existUserByEmail(localSignUpReq.getEmail());
        if (isExist) {
            throw new ApiException(ExceptionEnum.UNAUTHORIZED);
        }

        // 2. make User
        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder();
        String encryptedPassword = encoder.encode(localSignUpReq.getPassword());
        localSignUpReq.setEncryptPassword(encryptedPassword);
        User user = this.userRepository.save(localSignUpReq.toEntity());

        // 3. sending Email
        // TODO 1. SES sandbox => production
        // TODO 2. Dispatch Queue (Front => Background)
        String token = this.makeEmailToken(user);
        //emailService.sendSimpleMessage("eiffeltop01@gmail.com", "hello", token);
        
        return user;
    }

    private String makeEmailToken(User user) {
        // 1. make mail token
        Instant expiredAt = Instant.now().plus(12, ChronoUnit.HOURS);
        String token = new String(Base64.encode(String.format("%s%s", user.getId(), expiredAt.toString()).getBytes()), StandardCharsets.UTF_8);
        this.registrationEmailTokenRepository.save(RegistrationEmailToken.builder()
                .expiredAt(expiredAt)
                .user(user)
                .emailToken(token)
                .build());

        // TODO remove
        System.out.println(token);
        return token;
    }

    @Override
    public UserDTO.UserWithTokenRes signIn(UserDTO.LocalSignUpReq localSignUpReq) {
        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder();

        List<User> users = this.userRepository.findByEmail(localSignUpReq.getEmail());
        User user = users.stream().findFirst().orElse(null);
        if (user == null) {
            throw new ApiException(ExceptionEnum.SIGN_IN_USER_NOT_FOUND);
        }

        if (!user.getIsActive()) {
            throw new ApiException(ExceptionEnum.SIGN_IN_NOT_ACTIVE);
        }

        if (!encoder.matches(localSignUpReq.getPassword(), user.getPassword())) {
            throw new ApiException(ExceptionEnum.SIGN_IN_MISS_PASSWORD);
        }

        // make token
        Authentication authentication = new UserAuthentication(user.getId().toString(), null, null);
        String token = JwtTokenProvider.generateToken(authentication);
        return UserDTO.UserWithTokenRes.builder()
                .token(token)
                .build();
    }

    @Override
    public boolean verifyEmail(UserDTO.LocalEmailAuthReq localEmailAuthReq) {
        List<RegistrationEmailToken> tokens = this.registrationEmailTokenRepository.findByEmailToken(localEmailAuthReq.getToken());
        RegistrationEmailToken token = tokens.stream().findFirst().orElse(null);
        if (token == null) {
            throw new ApiException(ExceptionEnum.MISMATCH_EMAIL_TOKEN);
        }
        if (token.getExpiredAt().compareTo(Instant.now()) > 0) {
            throw new ApiException(ExceptionEnum.EXPIRED_EMAIL_TOKEN);
        }
        User user = token.getUser();
        user.setIsActive(true);
        this.userRepository.save(user);
        return true;
    }

    @Override
    public boolean existUserByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

}
