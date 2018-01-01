package com.bookstore.services.api;

import com.bookstore.domain.*;
import com.bookstore.domain.security.LoginStatus;
import org.springframework.dao.DataAccessException;

public interface LoginService {

    LoginStatus getStatus()throws DataAccessException;

    LoginStatus login(String username, String password)throws DataAccessException;
}