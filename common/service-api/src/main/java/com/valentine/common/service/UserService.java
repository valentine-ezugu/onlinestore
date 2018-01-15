package com.valentine.common.service;

import com.valentine.domain.User;
import com.valentine.domain.security.UserRole;
import org.springframework.dao.DataAccessException;

import java.util.Set;

public interface UserService {
    User createUser(User user, Set<UserRole> userRoles) throws Exception;


    User save(User user) throws DataAccessException;

    User findByUsername(String username) throws DataAccessException;

    User getUserById(Long id) throws DataAccessException;

}

