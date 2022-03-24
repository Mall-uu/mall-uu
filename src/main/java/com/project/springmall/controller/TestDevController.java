package com.project.springmall.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("dev")
public class TestDevController {
    
    @GetMapping("/api")
    public String test() {
        return "dev profile 성공!";
    }

}
