package com.nicki.enterpriseprojektarbete.user;

import com.nicki.enterpriseprojektarbete.user.dataObjects.UserModelDAO;
import com.nicki.enterpriseprojektarbete.user.dataObjects.UserScoreDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class UserModelService implements UserDetailsService {

    private final UserModelDAO userModelDAO;
    private final UserModelRepo userModelRepo;

    @Autowired
    public UserModelService(UserModelDAO userModelDAO, UserModelRepo userModelRepo) {
        this.userModelDAO = userModelDAO;
        this.userModelRepo = userModelRepo;
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

    public void updateUsername(Long userId, String newUsername) {
        UserModel user = userModelRepo.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setUsername(newUsername);
        userModelRepo.save(user);
    }

    public boolean usernameExists(String username) {
        return userModelRepo.findByUsername(username) != null;
    }

    public List<UserScoreDTO> getAllUsersWithScores() {
        List<UserScoreDTO> users = userModelDAO.findAllUserScores();

        return users;
    }


}
