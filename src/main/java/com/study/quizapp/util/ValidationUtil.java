package com.study.quizapp.util;

import com.study.quizapp.model.Question;
import java.util.*;
import java.util.stream.Stream;

public class ValidationUtil {

    private static final List<String> categories = new ArrayList<>(List.of("Python", "Java", "C++"));
    private static final List<String> difficultyLevel = new ArrayList<>(List.of("Easy", "Medium", "Hard"));

    public List<String> isQuestionValid(Question question) {

        Set<String> options = Stream.of(
                        question.getOption1(),
                        question.getOption2(),
                        question.getOption3(),
                        question.getOption4())
                .collect(HashSet::new, HashSet::add, HashSet::addAll);

        List<String> errors = new ArrayList<>(isCategoryValid(question.getCategory()));

        if (null == question.getQuestion_title()) {
            errors.add("Question title is required, It cannot be null please enter a valid title.");
        }
        else if (question.getQuestion_title().isBlank()) {
            errors.add("Question title is mandatory, It cannot be blank or empty please enter a valid title.");
        }

        if (null == question.getDifficultylevel()) {
            errors.add("Difficulty level is required, It cannot be null please enter a valid Difficulty level.");
        }
        else if (question.getDifficultylevel().isBlank()) {
           errors.add("Difficulty level is mandatory, It cannot be blank or empty please enter a valid Difficulty level.");
        }
        if (!difficultyLevel.contains(question.getDifficultylevel())) {
            errors.add("Invalid Difficulty level. Please select either 'Easy', 'Medium' or 'Hard' as the Difficulty level.");
        }

        if (null == question.getOption1()) {
            errors.add("Option No.1 is required, It cannot be null please enter a valid option.");
        }
        else if (question.getOption1().isBlank()) {
            errors.add("Option No.1 is mandatory, It cannot be blank or empty please enter a valid option.");
        }

        if (null == question.getOption2()) {
            errors.add("Option No.2 is required, It cannot be null please enter a valid option.");
        }
        else if (question.getOption2().isBlank()) {
            errors.add("Option No.2 is mandatory, It cannot be blank or empty please enter a valid option.");
        }

        if (null == question.getOption3()) {
            errors.add("Option No.3 is required, It cannot be null please enter a valid option.");
        }
        else if (question.getOption3().isBlank()) {
            errors.add("Option No.3 is mandatory, It cannot be blank or empty please enter a valid option.");
        }

        if (null == question.getOption4()) {
            errors.add("Option No.4 is required, It cannot be null please enter a valid option.");
        }
        else if (question.getOption4().isBlank()) {
            errors.add("Option No.4 is mandatory, It cannot be blank or empty please enter a valid option.");
        }

        if (!(options.size() == 4)) {
            errors.add("Invalid options. Please ensure that each option is distinct and clearly different from one another.");
        }

        if (null == question.getRight_answer()) {
            errors.add("Right answer is required, It cannot be null please enter a valid right answer.");
        }
        else if (question.getRight_answer().isBlank()) {
            errors.add("Right answer is mandatory, It cannot be blank or empty please enter a valid right answer.");
        }
        if (!options.contains(question.getRight_answer())) {
            errors.add("Invalid right answer. Please enter a valid right answer from the options provided.");
        }
        return errors;
    }

    public List<String> isCategoryValid(String category) {
        List<String> errors = new ArrayList<>();

        if (null == category) {
            errors.add("Category is required, It cannot be null please enter a valid category.");
        }
        else if (category.isBlank()) {
            errors.add("Category is mandatory, It cannot be blank or empty please enter a valid category.");
        }
        if (!categories.contains(category)) {
            errors.add("Invalid category. Please select either 'Python', 'Java' or 'C++' as the category.");
        }
        return errors;
    }
}
