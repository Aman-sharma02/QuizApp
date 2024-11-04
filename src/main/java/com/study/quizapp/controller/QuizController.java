package com.study.quizapp.controller;

import com.study.quizapp.model.Response;
import com.study.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestParam String category, @RequestParam int totalQuestions, @RequestParam String title, @RequestParam String description) {
        return quizService.createQuiz(category, totalQuestions, title, description);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getQuizQuestions(@PathVariable int id) {
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<?> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses) {
        return quizService.calculatedResult(id, responses);

    }
}
