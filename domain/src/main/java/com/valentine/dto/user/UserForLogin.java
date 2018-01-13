package com.valentine.dto.user;

import com.valentine.domain.User;


public class UserForLogin {

    private String username;

    public UserForLogin(User user) {
        this.username = user.getUsername();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
