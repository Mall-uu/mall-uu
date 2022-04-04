package com.project.springmall.question.domain;

import org.springframework.stereotype.Component;

import com.project.springmall.question.service.QuestionDto;
import com.project.springmall.question.service.QuestionUserDto;

@Component
public class QuestionMapper {

    public QuestionDto toQuestionDto(Question question) {
        QuestionUser questionUser = question.getUserInfo();
        QuestionUserDto userInfo = null;
        if(questionUser != null) {
            userInfo = QuestionUserDto.builder()
                        .id(questionUser.getId())
                        .email(questionUser.getEmail())
                        .username(questionUser.getUsername())
                        .nickname(questionUser.getNickname())
                        .build();
        }

        return QuestionDto.builder()
                .id(question.getId())
                .type(question.getType())
                .related(question.getRelated())
                .category(question.getCategory())
                .title(question.getTitle())
                .content(question.getContent())
                .status(question.getStatus())
                .imageFile(question.getImageFile())
                .createdAt(question.getCreatedAt().toString())
                .userId(question.getUserId())
                .userInfo(userInfo)
                .build();
    }
    
}
