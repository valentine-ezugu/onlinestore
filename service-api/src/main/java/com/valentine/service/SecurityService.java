package com.valentine.service;


import org.springframework.dao.DataAccessException;
import org.springframework.security.core.AuthenticationException;

public interface SecurityService {

    String findLoggedInUsername() throws DataAccessException;

    void autoLogin(String username, String password) throws AuthenticationException;
}
