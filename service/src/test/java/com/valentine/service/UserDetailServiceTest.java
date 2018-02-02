package com.valentine.service;


import com.valentine.domain.Role;
import com.valentine.domain.User;
import com.valentine.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserDetailsServiceImpl.class)
public class UserDetailServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Test
    public void loadByUsernameTest() throws Exception {
        User user = new User();
        Role role = new Role();
        Set<Role> roles = new HashSet<>();
        user.setRoles(roles);
        user.setUsername("francis");
        user.setPassword("vfnfdlnfks");

        String username = "francis";

        when(userRepository.findByUsername(username)).thenReturn(user);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (
                Role role1 : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        userDetailsService.loadUserByUsername(username);
        Mockito.verify(userRepository).findByUsername(username);
    }
}


