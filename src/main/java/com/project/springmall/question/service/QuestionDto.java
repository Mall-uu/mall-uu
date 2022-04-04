package com.project.springmall.question.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.project.springmall.question.domain.Question;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class QuestionDto {

    private long id;
    private int type;
    private long related;
    private String category;
    private String title;
    private String content;
    private int status;
    private String imageFile;
    private String createdAt;
    private long userId;
    private QuestionUserDto userInfo;
    
    public Question toEntity() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Question question = Question.builder()
            .type(this.type)
            .related(this.related)
            .category(this.category)
            .title(this.title)
            .content(this.content)
            .status(this.status)
            .imageFile(this.imageFile)
            .createdAt(LocalDateTime.parse(this.createdAt, formatter))
            .userId(this.userId)
            .build();

        return question;
    }
}
