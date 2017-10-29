package com.bookstore.service.api;

import com.bookstore.domain.security.LoginStatus;

public interface LoginService {

    LoginStatus getStatus();

    LoginStatus login(String username, String password);
}
