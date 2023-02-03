package com.nicki.enterpriseprojektarbete.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserModelRepo extends JpaRepository<UserModel, Long> {

    UserModel findByUsername(String username);
}
