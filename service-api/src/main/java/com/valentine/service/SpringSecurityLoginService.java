package com.valentine.service;

import com.valentine.domain.security.LoginStatus;
import org.springframework.dao.DataAccessException;

public interface SpringSecurityLoginService extends LoginService {
    LoginStatus getStatus() throws DataAccessException;

    LoginStatus login(String username, String password) throws DataAccessException;
}
