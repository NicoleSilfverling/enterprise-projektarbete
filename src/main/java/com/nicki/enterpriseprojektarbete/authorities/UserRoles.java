package com.nicki.enterpriseprojektarbete.authorities;

import java.util.ArrayList;
import java.util.List;

import static com.nicki.enterpriseprojektarbete.authorities.UserPermissions.*;

public enum UserRoles {

    USER(List.of(USER_READ)),
    ADMIN(List.of(ADMIN_READ, ADMIN_WRITE));

    private final List<UserPermissions> permissionsList;

    UserRoles(List<UserPermissions> permissions) {
        this.permissionsList = permissions;
    }

    public List<UserPermissions> getPermissions() {
        return permissionsList;
    }

    public List<String> getGrantedAuthorities() {

        List<String> permissionsList = new ArrayList<>(getPermissions().stream().map(
                UserPermissions::getUserPermission
        ).toList());

        permissionsList.add(("ROLE_" + this.name()));

        return permissionsList;
    }
}
