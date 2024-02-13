package com.telusko.quizservice.service;

import com.telusko.quizservice.feign.QuizInterface;
import com.telusko.quizservice.model.QuestionWrapper;
import com.telusko.quizservice.model.Quiz;
import com.telusko.quizservice.model.Response;
import com.telusko.quizservice.repository.QuizRepository;
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
    private QuizRepository quizRepository;

    @Autowired
    private QuizInterface quizInterface;

    // create Quiz
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        // call "generate" url (xxxxx/question/generate) from Question Service project - use "RestTemplate"
        // xxxxx - localhost:8080 (in my machine)
        // Cannot use ip address, hard code port etc. in prod
        // Hence use "Feign Client" to interact with other services
        // Quiz Service searches for Question Service, need to use a server for this - Eureka Server (Service Registry) - from Netflix
        // Each service is Eureka Client
        // All services need to register themselves to Eureka Server
        // One microservice can search another service from the Eureka Server using Eureka Client

        // to get Question numbers
        List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionsIds(questions);

        quizRepository.save(quiz);

        return new ResponseEntity<String>("Creation Success", HttpStatus.CREATED);
    }

    // get Quiz questions
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quizId = quizRepository.findById(id);
        Quiz quiz = quizId.get();
        List<Integer> questionsIds = quiz.getQuestionsIds();
        return quizInterface.getQuestionsFromQuestionId(questionsIds);
    }

    // calculate Quiz Result
    public ResponseEntity<String> calculateResult(Integer id, List<Response> responses) {
        return quizInterface.getScore(responses);
    }

}
