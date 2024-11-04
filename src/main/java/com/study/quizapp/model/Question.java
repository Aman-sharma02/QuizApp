package com.study.quizapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String category;

    private String difficultylevel;

    private String option1;

    private String option2;

    private String option3;

    private String option4;

    private String question_title;

    private String right_answer;

}
