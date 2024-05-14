package com.firstProject.appQuiz.service;

import com.firstProject.appQuiz.dao.QuestionDao;
import com.firstProject.appQuiz.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;


    public ResponseEntity<List<Question>> getAllQuestions() {

        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> addQuestion(Question question) {


        try {
            questionDao.save(question);
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<String>("failed", HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<String> deleteQuestion(Long id) {
        try {

            if (questionDao.existsById(Math.toIntExact(id))) {
                questionDao.deleteById(Math.toIntExact(id));
                return new ResponseEntity<>("success", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Question not found", HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return  new ResponseEntity<String>("failed", HttpStatus.UNAUTHORIZED);

    }

    public String updateQuestion(int id, Question updatedQuestion) {

        Optional<Question> existingQuestionOptional = questionDao.findById(id);

        if (existingQuestionOptional.isPresent()) {
            Question existingQuestion = existingQuestionOptional.get();

            // Update the fields of the existing question
            existingQuestion.setQuestionTitle(updatedQuestion.getQuestionTitle());
            existingQuestion.setOption1(updatedQuestion.getOption1());
            existingQuestion.setOption2(updatedQuestion.getOption2());
            existingQuestion.setOption3(updatedQuestion.getOption3());
            existingQuestion.setOption4(updatedQuestion.getOption4());
            existingQuestion.setRightAnswer(updatedQuestion.getRightAnswer());
            existingQuestion.setDifficultylevel(updatedQuestion.getDifficultylevel());
            existingQuestion.setCategory(updatedQuestion.getCategory());

            // Save the updated question
            questionDao.save(existingQuestion);

            return "success";
        } else {
            // The question does not exist
            return "Question not found";
        }
    }
}
