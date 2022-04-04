package com.project.springmall.question;

import java.util.List;
import com.project.springmall.question.service.QuestionDto;
import com.project.springmall.question.service.QuestionService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questions")
public class QuestionController {
    
    private final QuestionService service;

    @PostMapping("/regist")
    public QuestionDto regist(@RequestBody QuestionDto dto) {
        return service.create(dto);
    }

    @GetMapping("/{userId}/list")
    public List<QuestionDto> getUserQuestions(@PathVariable long userId) {
        return service.getList(userId);
    }

    @GetMapping("/question/{id}")
    public List<QuestionDto> getQuestion(@PathVariable long id) {
        return service.getOne(id);
    }

    @GetMapping("/all/list")
    public List<QuestionDto> getAllQuestions() {
        return service.getAllList();
    }

    @DeleteMapping("/question/remove/{id}")
    public String remove(@PathVariable long id) {
        service.delete(id);
        return "question has been deleted successfully";
    }

}
