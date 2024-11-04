package com.study.quizapp.service;

import com.study.quizapp.dao.QuestionDao;
import com.study.quizapp.dao.QuizDao;
import com.study.quizapp.model.Question;
import com.study.quizapp.model.QuestionnWrapper;
import com.study.quizapp.model.Quiz;
import com.study.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;


    public ResponseEntity<?> createQuiz(String category, int totalQuestions, String title, String description) {

        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, totalQuestions);

        if(questions.isEmpty()) {
            return new ResponseEntity<>("No Questions found",HttpStatus.NOT_FOUND);
        }

        Quiz quiz = new Quiz();

        quiz.setTitle(title);
        quiz.setDescription(description);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Quiz Created Successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<?> getQuizQuestions(int id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        if(quiz.isPresent()) {
            List<Question> questionsFromDb = quiz.get().getQuestions();
            List<QuestionnWrapper> questionForUser = new ArrayList<>();
            for(Question question : questionsFromDb) {
                QuestionnWrapper questionnWrapper = new QuestionnWrapper(question.getId(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4(),question.getQuestion_title());
                questionForUser.add(questionnWrapper);
            }
            return new ResponseEntity<>(questionForUser, HttpStatus.OK);
        }
        return new ResponseEntity<>("Quiz Not Found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> calculatedResult(Integer id, List<Response> responses) {
        Optional<Quiz> quiz = quizDao.findById(id);
        int right = 0;
        int i = 0;
        if(quiz.isPresent()) {
            List<Question> questionsFromDb = quiz.get().getQuestions();
            if(questionsFromDb.size() > 0) {
                for(Response response: responses) {
                    if(response.getResponse().equals(questionsFromDb.get(i).getRight_answer()))
                        right++;

                    i++;
                }
                return new ResponseEntity<>("You're Score is " + right + "/" + questionsFromDb.size(), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("Questions Not Found in the Quiz", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>("Quiz Not Found", HttpStatus.NOT_FOUND);
    }
}
