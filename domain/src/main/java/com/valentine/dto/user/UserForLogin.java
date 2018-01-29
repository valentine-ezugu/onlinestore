package com.valentine.dto.user;


import com.valentine.domain.User;

public class UserForLogin {

    private String username;

    public String getUsername() {
        return username;
    }


   User user;

    public UserForLogin(User user) {
        this.user = user;
    }

    public UserForLogin() {
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
