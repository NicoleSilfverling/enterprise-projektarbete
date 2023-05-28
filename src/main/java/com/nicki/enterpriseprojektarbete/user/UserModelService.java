package com.nicki.enterpriseprojektarbete.user;

import com.nicki.enterpriseprojektarbete.authorities.UserRoles;
import com.nicki.enterpriseprojektarbete.configurations.AppPasswordConfig;
import com.nicki.enterpriseprojektarbete.user.dataObjects.UserModelDAO;
import com.nicki.enterpriseprojektarbete.user.dataObjects.UserScoreDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserModelService implements UserDetailsService {

    private final UserModelDAO userModelDAO;
    private final UserModelRepo userModelRepo;
    private final AppPasswordConfig appPasswordConfig;

    @Autowired
    public UserModelService(UserModelDAO userModelDAO, UserModelRepo userModelRepo, AppPasswordConfig appPasswordConfig) {
        this.userModelDAO = userModelDAO;
        this.userModelRepo = userModelRepo;
        this.appPasswordConfig = appPasswordConfig;
    }


    @Override
    public UserModel loadUserByUsername(String username) throws UsernameNotFoundException {
        return userModelDAO.findUser(username);
    }

    public void saveUser(UserModel userModel){
        userModelDAO.save(userModel);
    }


    public void deleteUser(Long id){
        userModelRepo.deleteById(id);
    }

    public List<UserModel> findAllUsers(){
        return userModelRepo.findAll();
    }

    public UserModel getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            return userModelRepo.findByUsername(username);
        }

        return null;
    }



    public boolean usernameExists(String username) {
        return userModelRepo.findByUsername(username) != null;
    }

    public List<UserScoreDTO> getAllUsersWithScores() {
        List<UserScoreDTO> users = userModelDAO.findAllUserScores();

        return users;
    }


    public void registerUser(UserModel userModel) {
        if (usernameExists(userModel.getUsername())) {
            throw new IllegalArgumentException("Username already taken");
        }

        String role = String.valueOf(userModel.getAuthorities().iterator().next());
        switch (role) {
            case "Admin":
                userModel.setAuthorities(UserRoles.ADMIN.getGrantedAuthorities());
                break;
            case "User":
                userModel.setAuthorities(UserRoles.USER.getGrantedAuthorities());
                break;
        }

        userModel.setPassword(appPasswordConfig.bCryptPasswordEncoder().encode(userModel.getPassword()));
        userModel.setAccountNonExpired(true);
        userModel.setAccountNonLocked(true);
        userModel.setCredentialsNonExpired(true);
        userModel.setEnabled(true);
        userModel.setScore(0);

        System.out.println(userModel);

        userModelRepo.save(userModel);
    }

    public String updateUsername(UserModel currentUser, String newUsername) {
        if (usernameExists(newUsername)) {
            return "This username is already taken";
        }

        currentUser.setUsername(newUsername);
        userModelRepo.save(currentUser);

        // Update authentication object with new username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
                newUsername, authentication.getCredentials(), authentication.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuthentication);


        return null; // No error, username update successful
    }




}
