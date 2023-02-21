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

    private final AppPasswordConfig appPasswordConfig;
    private final UserModelRepo userModelRepo;
    private final UserModelService userModelService;

    @Autowired
    public UserModelController(AppPasswordConfig appPasswordConfig, UserModelRepo userModelRepo, UserModelService userModelService) {
        this.appPasswordConfig = appPasswordConfig;
        this.userModelRepo = userModelRepo;
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

        // check if username is unique
        if (userModelService.usernameExists(userModel.getUsername())) {
            result.rejectValue("username", "error.user", "Username already taken");
        }

        if (result.hasErrors()){
           return "register";
       }


        String role = String.valueOf(userModel.getAuthorities().iterator().next());

        switch (role) {
            case "Admin" ->  userModel.setAuthorities(UserRoles.ADMIN.getGrantedAuthorities());
            case "User" -> userModel.setAuthorities(UserRoles.USER.getGrantedAuthorities());
        }


        userModel.setPassword(appPasswordConfig.bCryptPasswordEncoder().encode(userModel.getPassword()));
        userModel.setAccountNonExpired(true);
        userModel.setAccountNonLocked(true);
        userModel.setCredentialsNonExpired(true);
        userModel.setEnabled(true);
        userModel.setScore(0);

        System.out.println(userModel);

        userModelRepo.save(userModel);

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
      if (userModelService.usernameExists(newUsername)) {
           model.addAttribute("error", "This username is already taken");
            return "editUser"; // return error page if new username is already taken
        }

        currentUser.setUsername(newUsername); // update username
        userModelService.saveUser(currentUser); // save updated user details


        // Update authentication object with new username
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
                newUsername, authentication.getCredentials(), authentication.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuthentication);

        return "redirect:/profile";
    }


    @GetMapping("/highscores")
    public String showUserScores(Model model) {
        List<UserScoreDTO> userScoreList = userModelService.getAllUsersWithScores();
        model.addAttribute("userScoreList", userScoreList);
        return "highscores";
    }

}
