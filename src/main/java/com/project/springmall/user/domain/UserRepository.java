/*
 * Copyright (c) 2022. Kang PilGyu
 * All rights reserved.
 */

package com.project.springmall.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);

    List<User> findByEmail(String email);
}