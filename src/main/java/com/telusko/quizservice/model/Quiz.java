package com.telusko.quizservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	
	@ElementCollection // different quiz can have same question // Quiz with multiple Q
	private List<Integer> questionsIds;
	
	
}
