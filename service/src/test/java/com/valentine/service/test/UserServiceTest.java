package com.valentine.service.test;

import com.valentine.domain.Role;
import com.valentine.domain.User;
import com.valentine.repository.RoleRepository;
import com.valentine.repository.UserRepository;
import com.valentine.service.UserService;

import com.valentine.utility.SecurityUtility;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

import static org.easymock.EasyMock.*;

@Transactional
@ContextConfiguration(classes = {  SecurityUtility.class, RoleRepository.class
})
public class UserServiceTest extends AbstractTest {

    @Autowired
    private SecurityUtility securityUtility;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Before
    public void setUp() {
        userRepository = createMock(UserRepository.class);
        ReflectionTestUtils.setField(userService, "userRepository", userRepository);
        roleRepository = createMock(RoleRepository.class);
    }

    @Test
    public void saveUser() throws Exception {
        User user = new User();
        user.setUsername("val");
        user.setPassword(securityUtility.passwordEncoder().encode("vsl"));
        user.setEmail("val@yahoo.com");

        Role role1 = role1 = roleRepository.findByname("USER");
        Set<Role> roles = new HashSet<>();

        roles.add(role1);
        user.setRoles(roles);

        expect(userRepository.save(user)).andReturn(user);
        replay(userRepository);

        User user1 = userService.save(user);
        verify(userRepository);

        Assert.assertNotNull(user1);
        Assert.assertEquals("val", user1.getUsername());
    }


    @Test
    public void createUserTest() throws Exception {

        User user = new User();
        user.setUsername("valentine");

        expect(userRepository.findByUsername(anyString())).andReturn(user);
        replay(userRepository);

        User user1 = userService.findByUsername("valentine");
        verify();

        Assert.assertEquals("valentine", user1.getUsername());
        user1.setFirstName("Ezugu");

        if (user1 != null) {
            logger.info("do nothing ");

        } else {

            Role role = roleRepository.findByname("USER");

            Set<Role> roles = new HashSet<>();
            roles.add(role);

            user1.setRoles(roles);

            expect(userRepository.save(user1)).andReturn(user1);
            replay(userRepository);

            User userCreated = userService.createUser(user1);
            verify();

            Assert.assertNotNull(userCreated);
            Assert.assertEquals("Ezugu", userCreated.getFirstName());

        }
    }
}

