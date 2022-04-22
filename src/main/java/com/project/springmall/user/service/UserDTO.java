/*
 * Copyright (c) 2022. Kang PilGyu
 * All rights reserved.
 */

package com.project.springmall.user.service;

import com.project.springmall.common.BaseDTO;
import com.project.springmall.user.domain.User;
import lombok.*;

import java.io.Serializable;
import java.security.KeyStore;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserDTO extends BaseDTO implements Serializable {
    private final String password;
    private final String email;
    private final String username;
    private final String nickname;
    private final String snsType;
    private final String snsId;

    public User toEntity() {
        return User.builder()
                .email(this.email)
                .password(this.password)
                .username(this.username)
                .nickname(this.nickname)
                .snsType(this.snsType)
                .snsId(this.snsId)
                .build();
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class LocalSignUpReq {
        private String password;
        private String email;

        @Builder
        public LocalSignUpReq(String email, String password) {
            this.password = password;
            this.email = email;
        }

        public void setEncryptPassword(String encryptedPassword) {
            this.password = encryptedPassword;
        }

        public User toEntity() {
            return User.builder()
                    .email(this.email)
                    .password(this.password)
                    .username(null)
                    .nickname(null)
                    .snsType(null)
                    .snsId(null)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class LocalEmailAuthReq {
        private String token;
        @Builder
        public LocalEmailAuthReq(String token) {
            this.token = token;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UserWithTokenRes {
        private String token;
        @Builder
        public UserWithTokenRes(String token) {
            this.token = token;
        }

    }

}
