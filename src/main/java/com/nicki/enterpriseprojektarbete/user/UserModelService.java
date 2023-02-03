package com.nicki.enterpriseprojektarbete.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserModelService implements UserDetailsService {

    private final UserModelRepo userModelRepo;

    @Autowired
    public UserModelService(UserModelRepo userModelRepo) {
        this.userModelRepo = userModelRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userModelRepo.findByUsername(username);
    }
}
