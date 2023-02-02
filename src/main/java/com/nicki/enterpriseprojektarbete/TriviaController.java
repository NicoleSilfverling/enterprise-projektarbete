package com.nicki.enterpriseprojektarbete;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Controller
public class TriviaController {

    @GetMapping("/quiz")
    public String callTriviaApi(Model model){
        final String url = "https://the-trivia-api.com/api/questions?categories=film_and_tv&limit=6&difficulty=easy";

        RestTemplate restTemplate = new RestTemplate();

        TriviaModel[] triviaModels;

        triviaModels = restTemplate.getForObject(url, TriviaModel[].class);

        System.out.println(Arrays.toString(triviaModels));

        model.addAttribute("trivia", Arrays.asList(triviaModels));
        return "quiz";

    }

}
