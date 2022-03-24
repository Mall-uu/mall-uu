package com.project.springmall.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("prod")
public class TestProdController {
    
    @GetMapping("/api")
    public String test() {
        return "prod profile 성공!";
    }
}
