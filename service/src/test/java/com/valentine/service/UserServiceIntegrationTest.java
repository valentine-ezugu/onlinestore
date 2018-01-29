package com.valentine.service;

import com.valentine.domain.Role;
import com.valentine.domain.User;
import com.valentine.repository.RoleRepository;
import com.valentine.utility.SecurityUtility;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

@Transactional
public class UserServiceIntegrationTest extends AbstractTest {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SecurityUtility securityUtility;

    @Test
    public void addUserTest() throws Exception {
        LOGGER.debug("test: save");
        User user1 = new User();
        user1.setUsername("V");
        user1.setPassword(securityUtility.passwordEncoder().encode("A"));
        user1.setEmail("valentineezugu@yahoo.com");
        Role role1 = roleRepository.findByname("USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role1);
        userService.createUser(user1);
        assert (user1.getUsername().equals("V"));
    }

    @Test
    public void findByUsernameTest() throws Exception {
        LOGGER.debug("test: findByUsernameTest()");
        User user = userService.findByUsername("V");
        assertNotNull(user);
        assertNotSame("james", "V");
    }

    @Test
    public void getUserByIdTest() throws Exception {
        LOGGER.debug("test: getUserByIdTest()");
        User user = userService.getUserById(2L);
        assert (user.getUsername().matches("V"));
    }

}
