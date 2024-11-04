package com.study.quizapp.service;

import com.study.quizapp.model.Question;
import com.study.quizapp.dao.QuestionDao;
import com.study.quizapp.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final ValidationUtil validationUtil = new ValidationUtil();

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<?> getAllQuestions() {
        List<Question> questions;
        try {
            questions = questionDao.findAll();
        }
        catch (Exception e) {
            // Add logging for error
            return new ResponseEntity<>(
                    "<div style='text-align: center;'> <br><br>\n" +
                            "<b>Oops! Something went wrong.</b><br><br>\n" +
                            "We encountered an unexpected issue on our end. Please try refreshing the page or come back later.<br>\n" +
                            "If the problem persists, contact our support team for assistance.\n" +
                            "</div>",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        if (questions.isEmpty()) {
            return new ResponseEntity<>(
                    "<div style='text-align: center;'><br><br>\n" +
                            "<b>No Questions Available</b><br><br>\n" +
                            "Currently, there are no questions available.<br>\n" +
                            "Please check back later for updates.\n" +
                            "</div>", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<?> getQuestionsByCategory(String category) {

        List<String> validationErrors = validationUtil.isCategoryValid(category);
        if(!validationErrors.isEmpty()) {
            return validationFailure(validationErrors);
        }
        List<Question> questions;
        try {
            questions = questionDao.findByCategory(category);
        }
        catch (Exception e) {
            // Add logging for error
            return new ResponseEntity<>(
                    "<div style='text-align: center;'> <br><br>\n" +
                            "<b>Oops! Something went wrong.</b><br><br>\n" +
                            "We encountered an unexpected issue on our end. Please try refreshing the page or come back later.<br>\n" +
                            "If the problem persists, contact our support team for assistance.\n" +
                            "</div>",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        if(questions.isEmpty()) {
            return new ResponseEntity<>(
                    "<div style='text-align: center;'><br><br>\n" +
                            "<b>No Questions Available for " + category + "</b><br><br>\n" +
                            "Currently, there are no questions available.<br>\n" +
                            "Please check back later for updates.\n" +
                            "</div>", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<?> addQuestion(Question question) {

        List<String> validationErrors = validationUtil.isQuestionValid(question);

        if (!validationErrors.isEmpty()) {
            return validationFailure(validationErrors);
        }

        try {
            questionDao.save(question);
        }
        catch (Exception e) {
            // Add logging for error
            return new ResponseEntity<>(
                    "<div style='text-align: center;'> <br><br>\n" +
                            "<b>Oops! Something went wrong.</b><br><br>\n" +
                            "We encountered an unexpected issue on our end. Please try refreshing the page or come back later.<br>\n" +
                            "If the problem persists, contact our support team for assistance.\n" +
                            "</div>",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        return new ResponseEntity<>(
                "<div style='text-align: center;'><br><br>\n" +
                        "<b>Question added successfully.</b>" +
                        "</div>", HttpStatus.CREATED);
    }

    public ResponseEntity<?> validationFailure(List<String> validationErrors) {
        // Build the error message string with each error on a new line
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("<div style='text-align: center;'><br><br>\n")
                .append("<b>Errors:</b><br>\n")
                .append("<div style='text-align: left; display: inline-block;'><br>\n");


        // Append each error to the message, followed by a line break
        int errorCount = 1;
        for (String error : validationErrors) {
            errorMessage.append(errorCount)
                    .append(".) ")
                    .append(error).append("<br>\n");
            ++errorCount;
        }

        // Close the div tag
        errorMessage.append("</div></div>");

        // Return the response entity with the error message and HTTP status
        return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
    }
}
