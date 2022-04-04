package com.project.springmall.question.domain;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{

    @EntityGraph(attributePaths = {"userInfo"})
    List<Question> findAll();
    List<Question> findByUserId(long userId);
    @EntityGraph(attributePaths = {"userInfo"})
    List<Question> findByIdOrRelated(long id, long related);
    @Transactional
    void deleteByIdOrRelated(long id, long related);
}
