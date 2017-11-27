package com.adminportal.dto.user;

import com.adminportal.domain.User;

public class LoginInfo {
    private String username;
    private String password;

    public LoginInfo() {
    }

    public LoginInfo(User user) {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
