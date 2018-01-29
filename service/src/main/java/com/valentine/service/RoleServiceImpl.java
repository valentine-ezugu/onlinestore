package com.valentine.service;

import com.valentine.domain.Role;
import com.valentine.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Pc on 1/28/2018.
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(String name) throws DataAccessException {
        return roleRepository.findByname(name);
    }
}
