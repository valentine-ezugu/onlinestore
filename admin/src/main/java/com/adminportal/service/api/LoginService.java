package com.adminportal.service.api;

import com.adminportal.domain.security.LoginStatus;


public interface LoginService {
    LoginStatus getStatus();

    LoginStatus login(String username, String password);
}
