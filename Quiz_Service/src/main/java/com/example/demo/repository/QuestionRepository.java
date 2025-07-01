package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.models.Answer;
import com.example.demo.models.Question;
import com.example.demo.models.QuestionWrapper;

public interface QuestionRepository extends JpaRepository<Question, Integer>{
	 @Query(value = "SELECT * FROM question WHERE subject = :subject ORDER BY RAND() LIMIT 10", nativeQuery = true)
	List<Question> findBySubject(String subject);
	 
	 @Query("SELECT q FROM Question q WHERE q.id IN :ids")
	 List<QuestionWrapper>findQuestionsByIds(@Param("ids") List<Question> questionIds);




}



