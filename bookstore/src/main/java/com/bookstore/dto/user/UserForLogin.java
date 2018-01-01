package com.bookstore.dto.user;

import com.bookstore.domain.*;


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
