package com.nicki.enterpriseprojektarbete.trivia;

import com.nicki.enterpriseprojektarbete.quiz.QuizModel;
import com.nicki.enterpriseprojektarbete.quiz.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TriviaService {

    private final QuizRepo quizRepo;
    private final TriviaRepo triviaRepo;

    @Autowired
    public TriviaService(QuizRepo quizRepo, TriviaRepo triviaRepo) {
        this.quizRepo = quizRepo;
        this.triviaRepo = triviaRepo;
    }





    public List<TriviaModel> getTriviaById(String id) {
        Optional<QuizModel> quizModel = quizRepo.findById(Long.valueOf(id));

        final String url = "https://the-trivia-api.com/api/questions?categories=" +
                quizModel.get().getCategory() +
                "&limit=" +
                quizModel.get().getNrOfQuestions()+
                "&difficulty=" +
                quizModel.get().getDifficulty();

        RestTemplate restTemplate = new RestTemplate();
        TriviaModel[] triviaModels = restTemplate.getForObject(url, TriviaModel[].class);

        return Arrays.asList(triviaModels);
    }

}
