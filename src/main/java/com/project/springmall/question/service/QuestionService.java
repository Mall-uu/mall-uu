package com.project.springmall.question.service;

import java.util.List;
import java.util.stream.Collectors;
import com.project.springmall.question.domain.QuestionMapper;
import com.project.springmall.question.domain.QuestionRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository repository;
    private final QuestionMapper mapper;
    
    public QuestionDto create(QuestionDto dto) {
        return mapper.toQuestionDto(repository.save(dto.toEntity()));
    }

    public List<QuestionDto> getList(long userId) {
        return repository.findByUserId(userId).stream().map(question -> mapper.toQuestionDto(question)).collect(Collectors.toList());
    }
    
    public List<QuestionDto> getOne(long id) {
        return repository.findByIdOrRelated(id, id).stream().map(question -> mapper.toQuestionDto(question)).collect(Collectors.toList());
    }

    public List<QuestionDto> getAllList() {
        return repository.findAll().stream().map(question -> mapper.toQuestionDto(question)).collect(Collectors.toList());
    }

    public void delete(long id) {
        repository.deleteByIdOrRelated(id, id);
    }

}
