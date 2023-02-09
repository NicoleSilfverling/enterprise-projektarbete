package com.nicki.enterpriseprojektarbete.user;

import com.nicki.enterpriseprojektarbete.authorities.UserRoles;
import com.nicki.enterpriseprojektarbete.configurations.AppPasswordConfig;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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



    @GetMapping("/register")
    public String showRegistrationForm(UserModel userModel) {

        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserModel userModel, BindingResult result, Model model) {

        System.out.println(userModel);


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
}
