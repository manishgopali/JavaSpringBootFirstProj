package com.firstProject.appQuiz.dao;

import com.firstProject.appQuiz.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz,Integer> {
}
