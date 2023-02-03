package com.nicki.enterpriseprojektarbete.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


@RestController
public class QuizController {

    private final QuizRepo quizRepo;

    @Autowired
    public QuizController(QuizRepo quizRepo) {
        this.quizRepo = quizRepo;
    }



    @PostMapping("/addQuiz")
    public ResponseEntity<QuizModel> createQuiz(@RequestBody QuizModel quiz){
        try {
            QuizModel newQuiz = quizRepo.save(new QuizModel(quiz.getNrOfQuestions(), quiz.getDifficulty(), quiz.getCategory()));

            return new ResponseEntity<>(newQuiz, HttpStatus.CREATED);

        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
