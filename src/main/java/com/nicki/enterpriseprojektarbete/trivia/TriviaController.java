package com.nicki.enterpriseprojektarbete.trivia;

import com.nicki.enterpriseprojektarbete.quiz.QuizModel;
import com.nicki.enterpriseprojektarbete.quiz.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Optional;

@Controller
public class TriviaController {

    private final QuizRepo quizRepo;

    @Autowired
    public TriviaController(QuizRepo quizRepo) {
        this.quizRepo = quizRepo;
    }


    @GetMapping("/quiz/{id}")
    public String callTriviaApi(Model model, @PathVariable String id){

        Optional<QuizModel> quizModel = quizRepo.findById(Long.valueOf(id));


        final String url = "https://the-trivia-api.com/api/questions?categories=" + quizModel.get().getCategory() + "&limit=" + quizModel.get().getNrOfQuestions()+ "&difficulty=" + quizModel.get().getDifficulty();

        System.out.println(quizModel.get().getCategory());
        System.out.println(quizModel.get().getDifficulty());
        System.out.println(quizModel.get().getNrOfQuestions());
        System.out.println(quizModel.get().getId());


        RestTemplate restTemplate = new RestTemplate();

        TriviaModel[] triviaModels;

        triviaModels = restTemplate.getForObject(url, TriviaModel[].class);


        model.addAttribute("trivia", Arrays.asList(triviaModels));
        return "quiz";

    }





/*
    @GetMapping("/quiz/{id}")
    public String callTriviaApi(Model model, @PathVariable String id){





        //TODO - get variables from saved quizzes in db
        int nrOfQuestions = 2;
        String difficulty = "easy";
        String category = "film_and_tv";

        final String url = "https://the-trivia-api.com/api/questions?categories=" + category + "&limit=" + nrOfQuestions+ "&difficulty=" + difficulty;



        RestTemplate restTemplate = new RestTemplate();

        TriviaModel[] triviaModels;

        triviaModels = restTemplate.getForObject(url, TriviaModel[].class);


        model.addAttribute("trivia", Arrays.asList(triviaModels));
        return "quiz";

    }*/

}
