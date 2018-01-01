package com.admin.service;

import com.adminportal.config.SecurityConfig;
import com.adminportal.domain.Book;
import com.adminportal.repository.RoleRepository;
import com.adminportal.repository.UserRepository;
import com.adminportal.service.api.UserService;
import com.adminportal.utility.SecurityUtility;
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

import static org.easymock.EasyMock.createMock;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SecurityConfig.class})
@Transactional
public class LogicTest {

    @Autowired
    private SecurityUtility securityUtility;

    @Autowired
    private UserService userService;

//   @Autowired
//    private DozerBeanMapperFactoryBean dozerBean;

    @Autowired
    private UserRepository userRepository;

    private RoleRepository roleRepository;

    @Test
    public void bookObjectExist() {
        Book book = new Book();
        Assert.assertNotNull(book);
    }

    @Before
    public void setUp() {
        userRepository = createMock(UserRepository.class);
        roleRepository = createMock(RoleRepository.class);
        ReflectionTestUtils.setField(userService, "userRepository", userRepository);
        ReflectionTestUtils.setField(userService, "roleRepository", roleRepository);

    }

//
//    @Test
//    public void tetDozerMapping() throws Exception {
//
//        org.dozer.Mapper mapper = dozerBean.getObject();
//
//        Book p1Domain = new Book();
//        p1Domain.setTitle("John Smith");
//        p1Domain.setId(25L);
//
//        BookDetail p1Dto = new BookDetail();
//
//        //map source: p1Domain to target:p1Dto using "dozer-bean-mappings.xml" map-id: person
//
//        mapper.map(p1Domain, p1Dto, "person");
//
//        Assert.assertEquals(p1Domain.getTitle(), p1Dto.gettitle());
//        Assert.assertEquals(p1Domain.getId(), p1Dto.getid());
//    }
}

        //    @Test
//    public void createUserTest() throws Exception {
//        User user = new User();
//
//        User localUser = new User();
//
//        Set<UserRole> userRoles = new HashSet<>();
//        Role role1 = new Role();
//
//        role1.setRoleId(3);
//        role1.setName("ADMIN");
//        userRoles.add(new UserRole(user, role1));
//        userService.createUser(user, userRoles);
//
//        expect(localUser = userService.save(user)).andReturn(localUser);
//
//    }
//}