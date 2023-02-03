package com.nicki.enterpriseprojektarbete;

import com.nicki.enterpriseprojektarbete.quiz.QuizModel;
import com.nicki.enterpriseprojektarbete.quiz.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final QuizRepo quizRepo;

    @Autowired
    public HomeController(QuizRepo quizRepo) {
        this.quizRepo = quizRepo;
    }




    @GetMapping("/")
    public String home(Model model) {
        List<QuizModel> quizzes = quizRepo.findAll();
        model.addAttribute("quizzes", quizzes);
        return "home";
    }
}
