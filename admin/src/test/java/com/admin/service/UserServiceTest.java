package com.admin.service;

import com.admin.ws.AbstractTest;
import com.adminportal.domain.User;
import com.adminportal.domain.security.Role;
import com.adminportal.domain.security.UserRole;
import com.adminportal.repository.RoleRepository;
import com.adminportal.repository.UserRepository;
import com.adminportal.service.api.UserService;
import com.adminportal.utility.SecurityUtility;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

import static org.easymock.EasyMock.*;

@Transactional
public class UserServiceTest extends AbstractTest {

    @Autowired
    private SecurityUtility securityUtility;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private RoleRepository roleRepository;

    @Before
    public void setUp() {
        userRepository = createMock(UserRepository.class);
        roleRepository = createMock(RoleRepository.class);
        ReflectionTestUtils.setField(userService, "userRepository", userRepository);
        ReflectionTestUtils.setField(userService, "roleRepository", roleRepository);

    }

    @Test
    public void saveUser() throws Exception {
        User user = new User();
        user.setUsername("vsl");
        user.setPassword(securityUtility.passwordEncoder().encode("vsl"));
        user.setEmail("val@yahoo.com");
        Set<UserRole> userRoles = new HashSet<>();
        Role role1 = new Role();
        role1.setRoleId(2);
        role1.setName("ROLE_USER");
        userRoles.add(new UserRole(user, role1));
        expect(userRepository.save(user)).andReturn(user);
    }

    @Test
    public void findSave2() throws Exception {
        User user = new User();
        user.setId(1L);
        expect(userRepository.findOne(1L)).andReturn(user);
        replay(userRepository);
    }

}


