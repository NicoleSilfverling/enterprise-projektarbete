package com.nicki.enterpriseprojektarbete.user.dataObjects;

import com.nicki.enterpriseprojektarbete.user.UserModel;

public interface IUserModelDAO<T> {

    T findUser(String username);
    void save(UserModel userModel);
    void update(UserModel userModel);
    void delete(UserModel userModel);
}
