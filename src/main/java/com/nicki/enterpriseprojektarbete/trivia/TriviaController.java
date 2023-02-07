package com.nicki.enterpriseprojektarbete.trivia;

import com.nicki.enterpriseprojektarbete.quiz.QuizModel;
import com.nicki.enterpriseprojektarbete.quiz.QuizRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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


        RestTemplate restTemplate = new RestTemplate();

        TriviaModel[] triviaModels;

        triviaModels = restTemplate.getForObject(url, TriviaModel[].class);


        model.addAttribute("trivia", Arrays.asList(triviaModels));
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


}
