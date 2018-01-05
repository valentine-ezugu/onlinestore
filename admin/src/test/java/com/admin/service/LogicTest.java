package com.admin.service;

import com.adminportal.config.SecurityConfig;
import com.adminportal.domain.Book;
import com.adminportal.domain.User;
import com.adminportal.domain.security.Role;
import com.adminportal.domain.security.UserRole;
import com.adminportal.dto.book.BookDetailLite;
import com.adminportal.repository.BookRepository;
import com.adminportal.repository.RoleRepository;
import com.adminportal.repository.UserRepository;
import com.adminportal.service.api.UserService;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.easymock.EasyMock.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SecurityConfig.class})
@Transactional
public class LogicTest {

    @Autowired
    private UserService userService;

    @Autowired
    private Mapper mapper;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private BookRepository bookRepository;

    private RoleRepository roleRepository;

    @Test
    public void bookObjectExist() {
        Book book = new Book();
        Assert.assertNotNull(book);
    }

    @Before
    public void setUp() {
        userRepository = createMock(UserRepository.class);
        bookRepository = createMock(BookRepository.class);
        roleRepository = createMock(RoleRepository.class);
        mapper = createMock(Mapper.class);
        ReflectionTestUtils.setField(userService, "userRepository", userRepository);
        ReflectionTestUtils.setField(userService, "roleRepository", roleRepository);

    }


    @Test
    public void testDozerMapping() throws Exception {

        List<String> list = new ArrayList<>();
        list.add("mapping.xml");

        mapper = new DozerBeanMapper(list);

        Book p1Domain = new Book();
        p1Domain.setTitle("John Smith");
        p1Domain.setId(25L);

        BookDetailLite p1Dto = mapper.map(p1Domain, BookDetailLite.class, "bookDetailLiteId");

        Assert.assertEquals(p1Domain.getTitle(), p1Dto.getTitle());
        Assert.assertEquals(p1Domain.getId(), p1Dto.getId());
    }


    @Test
    public void createUserTest() throws Exception {
        User user = new User();
        Set<UserRole> userRoles = new HashSet<>();
        user.setUsername("valentine");

        expect(userRepository.findByUsername(anyObject())).andReturn(user);

        User localUser = new User();

        Role role1 = new Role();
        role1.setRoleId(3);
        role1.setName("ADMIN");
        userRoles.add(new UserRole(user, role1));

        if (localUser != null) {
            user.getUsername();
        } else {
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);

        }
        expect(localUser = userRepository.save(user)).andReturn(localUser);

    }


}
