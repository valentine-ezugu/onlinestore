package com.valentine.service;


import com.valentine.domain.Role;
import com.valentine.repository.RoleRepository;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RoleServiceImpl.class)
public class RoleServiceTest {

    @MockBean
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    @Rule
    public final ExpectedException exception =   ExpectedException.none();

    @Test
    public void findByUserRole() throws Exception {
        Role role = new Role();
        role.setName("ADMIN");

        when(roleRepository.findByname("ADMIN")).thenReturn(role);

        Role role1 = roleService.findByName("ADMIN");

        Mockito.verify(roleRepository).findByname("ADMIN");
        Assert.assertEquals("ADMIN", role1.getName());

    }

    @Test
    public void exceptionTest() throws Exception{

        when(roleRepository.findByname("ADMIN")).thenThrow(new  DataAccessException(""){
        });

        exception.expect(DataAccessException.class);
        roleService.findByName("ADMIN");
    }
}
