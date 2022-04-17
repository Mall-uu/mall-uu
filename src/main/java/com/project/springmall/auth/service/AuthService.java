/*
 * Copyright (c) 2022. Kang PilGyu
 * All rights reserved.
 */

package com.project.springmall.auth.service;

import com.project.springmall.user.service.UserDTO;
import com.project.springmall.user.domain.User;

public interface AuthService {

    User register(UserDTO.LocalSignUpReq localSignUpReq);

    UserDTO.UserWithTokenRes signIn(UserDTO.LocalSignUpReq localSignUpReq);

    boolean existUserByEmail(String email);
}
