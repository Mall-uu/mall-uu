/*
 * Copyright (c) 2022. Kang PilGyu
 * All rights reserved.
 */

package com.project.springmall.auth;

import com.project.springmall.common.BaseApiController;
import com.project.springmall.common.BaseController;
import com.project.springmall.user.service.UserDTO;
import com.project.springmall.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController extends BaseApiController {

    private final AuthService authService;

    @PostMapping("/auth/signup")
    public boolean signUp(@RequestBody UserDTO.LocalSignUpReq localSignUpReq) {
        this.authService.register(localSignUpReq);
        return false;
    }

//    @PostMapping("/auth/signin")
//    public

}
