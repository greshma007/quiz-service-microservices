package com.telusko.quizservice.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * To show only specific questions to user
 * 
 */
@Data
public class QuestionWrapper {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String questionTitle;
	private String option1;
	private String option2;
	private String option3;
	private String option4;

	public QuestionWrapper() {
		// do nothing
	}

	public QuestionWrapper(int id, String questionTitle, String option1, String option2, String option3,
			String option4) {
		this.id = id;
		this.questionTitle = questionTitle;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
	}
}
