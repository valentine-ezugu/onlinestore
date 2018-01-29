package com.valentine.service;

import com.valentine.domain.Role;
import org.springframework.dao.DataAccessException;

/**
 * Created by Pc on 1/28/2018.
 */
public interface RoleService {

    Role findByName(String name) throws DataAccessException;
}
