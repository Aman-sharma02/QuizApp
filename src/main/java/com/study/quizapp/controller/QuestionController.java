package com.study.quizapp.controller;

import com.study.quizapp.model.Question;
import com.study.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/allQuestions")
    public ResponseEntity<?> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping({"/category/{category}"})
    public ResponseEntity<?> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }
}
