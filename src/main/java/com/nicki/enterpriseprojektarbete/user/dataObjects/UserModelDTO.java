package com.nicki.enterpriseprojektarbete.user.dataObjects;

import com.nicki.enterpriseprojektarbete.user.UserModel;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserModelDTO {

    private final String username;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserModelDTO(UserModel userModel) {
        this.username = userModel.getUsername();
        this.authorities = userModel.getAuthorities();
    }

    public String getUsername() {
        return username;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
