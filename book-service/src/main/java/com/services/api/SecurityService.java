package com.services.api;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.AuthenticationException;

public interface SecurityService {

    String findLoggedInUsername() throws DataAccessException;

    void autologin(String username, String password) throws AuthenticationException;

}