/*
 * Copyright (c) 2022. Kang PilGyu
 * All rights reserved.
 */

package com.project.springmall.auth.service;

import com.project.springmall.common.BaseService;
import com.project.springmall.exception.ExceptionEnum;
import com.project.springmall.exception.api.ApiException;
import com.project.springmall.user.service.UserDTO;
import com.project.springmall.user.domain.User;
import com.project.springmall.user.domain.UserRepository;
import com.project.springmall.utils.JwtTokenProvider;
import com.project.springmall.utils.UserAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl extends BaseService implements AuthService {
    private final UserRepository userRepository;

    @Override
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
        // TODO

        return user;
    }

    @Override
    public UserDTO.UserWithTokenRes signIn(UserDTO.LocalSignUpReq localSignUpReq) {
        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder();

        List<User> users = this.userRepository.findByEmail(localSignUpReq.getEmail());
        User user = users.stream().findFirst().orElse(null);
        if (user == null) {
            throw new ApiException(ExceptionEnum.SIGN_IN_USER_NOT_FOUND);
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
    public boolean existUserByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

}
