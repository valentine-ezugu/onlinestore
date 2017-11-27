package com.adminportal.dto.role;

import com.adminportal.domain.security.Role;


public class RoleInfo {

    public RoleInfo() {
    }

    private String name;

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
