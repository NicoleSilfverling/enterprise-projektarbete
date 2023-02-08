package com.nicki.enterpriseprojektarbete.user;

import com.nicki.enterpriseprojektarbete.user.dataObjects.UserModelDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

}
