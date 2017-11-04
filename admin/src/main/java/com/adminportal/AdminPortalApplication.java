package com.adminportal;

import com.adminportal.domain.security.Role;
import com.adminportal.domain.security.UserRole;
import com.adminportal.service.api.UserService;
import com.adminportal.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import java.util.HashSet;
import java.util.Set;


@Configuration
@SpringBootApplication
public class AdminPortalApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(AdminPortalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        com.adminportal.domain.User user1 = new com.adminportal.domain.User();
        user1.setUsername("admin");
        user1.setPassword(SecurityUtility.passwordEncoder().encode("admin"));
        user1.setEmail("admin@yahoo.com");
        Set<UserRole> userRoles = new HashSet<>();
        Role role1 = new Role();
        role1.setRoleId(0);
        role1.setName("ADMIN");//ROLE_ADMIN
        userRoles.add(new UserRole(user1, role1));
        userService.createUser(user1, userRoles);
    }

}
