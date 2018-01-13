package com.adservice.impl;

import com.repository.repo.RoleRepository;
import com.repository.repo.UserRepository;
import com.adservice.api.UserService;
import com.domain.domain.User;
import com.domain.domain.security.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws DataAccessException {

        User localUser = userRepository.findByUsername(user.getUsername());

        if (localUser != null) {
            LOG.info("user {} already exists. Nothing will be done.", user.getUsername());
        } else {
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);

            localUser = userRepository.save(user);
        }
        return localUser;
    }

    @Override
    public User save(User user) throws DataAccessException {
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) throws DataAccessException {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserById(Long id) throws DataAccessException {
        return userRepository.findOne(id);
    }
}