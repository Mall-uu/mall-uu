package com.project.springmall.auth.domain;

import com.project.springmall.auth.domain.RegistrationEmailToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationEmailTokenRepository extends JpaRepository<RegistrationEmailToken, Integer> {
    List<RegistrationEmailToken> findByEmailToken(String token);
}