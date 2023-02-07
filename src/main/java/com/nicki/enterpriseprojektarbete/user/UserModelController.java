package com.nicki.enterpriseprojektarbete.user;

import com.nicki.enterpriseprojektarbete.authorities.UserRoles;
import com.nicki.enterpriseprojektarbete.configurations.AppPasswordConfig;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserModelController {

    private final AppPasswordConfig appPasswordConfig;
    private final UserModelRepo userModelRepo;

    @Autowired
    public UserModelController(AppPasswordConfig appPasswordConfig, UserModelRepo userModelRepo) {
        this.appPasswordConfig = appPasswordConfig;
        this.userModelRepo = userModelRepo;
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
}
