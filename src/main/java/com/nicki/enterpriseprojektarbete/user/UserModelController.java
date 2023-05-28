package com.nicki.enterpriseprojektarbete.user;

import com.nicki.enterpriseprojektarbete.authorities.UserRoles;
import com.nicki.enterpriseprojektarbete.configurations.AppPasswordConfig;
import com.nicki.enterpriseprojektarbete.user.dataObjects.UserScoreDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserModelController {


    private final UserModelService userModelService;

    @Autowired
    public UserModelController( UserModelService userModelService) {

        this.userModelService = userModelService;
    }

    @GetMapping("/profile")
    public String showProfile(Model model) {
        UserModel currentUser = userModelService.getCurrentUser();
        model.addAttribute("user", currentUser);
        return "profile";
    }


    @GetMapping("/register")
    public String showRegistrationForm(UserModel userModel) {

        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserModel userModel, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        try {
            userModelService.registerUser(userModel);
        } catch (IllegalArgumentException e) {
            result.rejectValue("username", "error.user", e.getMessage());
            return "register";
        }

        return "login";
    }


    @GetMapping("/users")
    public String displayAllUsers(Model model) {
        List<UserModel> users = userModelService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }



    /*
    *   DELETE A USER
    *   @DeleteMapping works in Postman but gives Method Not Allowed status=405 in localhost.
    *   @PostMapping works with localhost
    *   */
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable Long id) {

        if (id == null) {
            return "users";
        }
        userModelService.deleteUser(id);
        return "users";
    }


    @PostMapping("/users/delete")
    public String deleteUserById(@RequestParam Long id) {
        userModelService.deleteUser(id);
        return "redirect:/users";
    }

    @PostMapping("/updateUsername")
    public String updateUsername(@RequestParam("username") String newUsername, Model model) {
        UserModel currentUser = userModelService.getCurrentUser();

        String error = userModelService.updateUsername(currentUser, newUsername);
        if (error != null) {
            model.addAttribute("error", error);
            return "editUser";
        }

        return "redirect:/profile";
    }



    @GetMapping("/highscores")
    public String showUserScores(Model model) {
        List<UserScoreDTO> userScoreList = userModelService.getAllUsersWithScores();
        model.addAttribute("userScoreList", userScoreList);
        return "highscores";
    }

}
