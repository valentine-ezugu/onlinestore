package com.adservice.api;

import com.domain.domain.security.LoginStatus;
import org.springframework.dao.DataAccessException;


public interface LoginService {

    LoginStatus getStatus() throws DataAccessException;

    LoginStatus login(String username, String password) throws DataAccessException;


}