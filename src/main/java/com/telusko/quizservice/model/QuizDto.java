package com.telusko.quizservice.model;

import lombok.Data;

/**
 * Param class
 */
@Data
public class QuizDto {

    // params from user
    String category;
    int numQ;
    String title;
}
