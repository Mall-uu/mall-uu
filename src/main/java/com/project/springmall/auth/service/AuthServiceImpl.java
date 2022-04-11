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
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public boolean existUserByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

}
