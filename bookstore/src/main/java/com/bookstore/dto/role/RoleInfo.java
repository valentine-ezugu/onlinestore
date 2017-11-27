package com.bookstore.dto.role;

import com.bookstore.domain.security.Role;


public class RoleInfo {
    private String name;


    public RoleInfo(String name) {
        this.name = name;
    }

    public RoleInfo(Role role) {
        name = role.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
