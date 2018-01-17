package com.valentine.service;

import com.valentine.utility.LoginStatus;
import org.springframework.dao.DataAccessException;


public interface LoginService {

    LoginStatus getStatus() throws DataAccessException;

    LoginStatus login(String username, String password) throws DataAccessException;


}
