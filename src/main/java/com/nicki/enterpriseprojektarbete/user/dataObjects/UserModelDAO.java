package com.nicki.enterpriseprojektarbete.user.dataObjects;

import com.nicki.enterpriseprojektarbete.user.UserModel;
import com.nicki.enterpriseprojektarbete.user.UserModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserModelDAO implements IUserModelDAO<UserModel>{

    private final UserModelRepo userModelRepo;

    @Autowired
    public UserModelDAO(UserModelRepo userModelRepo) {
        this.userModelRepo = userModelRepo;
    }




    @Override
    public UserModel findUser(String username) {
        return userModelRepo.findByUsername(username);
    }

    @Override
    public void save(UserModel userModel) {
        userModelRepo.save(userModel);

    }

    @Override
    public void update(UserModel userModel) {

    }

    @Override
    public void delete(UserModel userModel) {

    }
}
