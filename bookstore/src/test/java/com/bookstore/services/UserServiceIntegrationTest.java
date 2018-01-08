package com.bookstore.services;

import com.bookstore.domain.User;
import com.bookstore.domain.security.Role;
import com.bookstore.domain.security.UserRole;
import com.bookstore.utility.SecurityUtility;
import com.bookstore.ws.AbstractTest;
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
    private com.bookstore.services.api.UserService userService;

    @Autowired
    private SecurityUtility securityUtility;

    @Test
    public void addUserTest() throws Exception {
        LOGGER.debug("test: save");
        User user1 = new User();
        user1.setUsername("V");
        user1.setPassword(securityUtility.passwordEncoder().encode("A"));
        user1.setEmail("valentineezugu@yahoo.com");
        Set<UserRole> userRoles = new HashSet<>();
        Role role1 = new Role();
        role1.setRoleId(1);
        role1.setName("ROLE_USER");
        userRoles.add(new UserRole(user1, role1));
        userService.createUser(user1, userRoles);
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
