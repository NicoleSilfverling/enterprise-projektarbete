package com.nicki.enterpriseprojektarbete.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserModelController {

    private final UserModelService userModelService;

    @Autowired
    public UserModelController(UserModelService userModelService) {
        this.userModelService = userModelService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(UserModel userModel) {

        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserModel userModel, BindingResult result, Model model) {
        userModelService.saveUser(userModel);



        return "home";
    }
}
