package com.firstProject.appQuiz.controller;

import com.firstProject.appQuiz.model.Question;
import com.firstProject.appQuiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestion()
    {
        return questionService.getAllQuestions();

    }

    @GetMapping("category/{category}")
    public ResponseEntity< List<Question>> getQuestionsByCategory(@PathVariable String category){

        return questionService.getQuestionsByCategory(category);

    }


    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question)
    {
        return questionService.addQuestion(question);
    }

    @DeleteMapping ("delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id)
    {
        return questionService.deleteQuestion(id);
    }


    @PutMapping ("update/{id}")
    public  String updateQuestion(@PathVariable int id, @RequestBody Question updatedQuestion) {
        return questionService.updateQuestion(id, updatedQuestion);
    }

}
