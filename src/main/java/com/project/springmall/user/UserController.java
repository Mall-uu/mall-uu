/*
 * Copyright (c) 2022. Kang PilGyu
 * All rights reserved.
 */

package com.project.springmall.user;

import com.project.springmall.user.domain.User;
import com.project.springmall.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/users")
    User get() throws Exception {
//        User user = new User();
//        user.setEmail("test@test.com");
//        user.setNickname("kang");
//        user.setUsername("kang");
//        user.setPassword("1q2w3e4r");
//        this.repository.save(user);
//        return this.repository.findById(1)
//                .orElseThrow(() -> new Exception("efe"));
        return null;
    }
}
