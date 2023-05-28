package com.nicki.enterpriseprojektarbete.trivia;

import com.nicki.enterpriseprojektarbete.quiz.QuizModel;
import com.nicki.enterpriseprojektarbete.quiz.QuizRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Controller
public class TriviaController {

    private final QuizRepo quizRepo;
    private final TriviaService triviaService;

    @Autowired
    public TriviaController(QuizRepo quizRepo, TriviaService triviaService) {
        this.quizRepo = quizRepo;
        this.triviaService = triviaService;
    }

    @GetMapping("/quiz/{id}")
    public String callTriviaApi(Model model, @PathVariable String id) {
        List<TriviaModel> triviaModels = triviaService.getTriviaById(id);

        model.addAttribute("trivia", triviaModels);
        return "quiz";
    }


    @GetMapping("/createQuiz")
    public String showCreateQuiz(QuizModel quizModel){
        return "createQuiz";
    }


    @PostMapping("/createQuiz")
    public String createQuiz(@Valid QuizModel quiz, BindingResult result, Model model){

        if(result.hasErrors()){
            return "createQuiz";
        }

        quizRepo.save(quiz);

        return "home";
    }



    @PostMapping("/checkAnswer")
    public void checkAnswer(Model model) {
        // create method to check if answer is right here
    }


}
