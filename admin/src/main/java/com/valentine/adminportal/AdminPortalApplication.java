package com.valentine.adminportal;

import com.valentine.common.utility.SecurityUtility;
import com.valentine.domain.User;
import com.valentine.domain.security.Role;
import com.valentine.domain.security.UserRole;
import com.valentine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EntityScan(basePackages = {"com.valentine.domain","com.valentine.service"})
@ComponentScan(basePackages = {"com.valentine.adminportal" ,"com.valentine.service", "com.valentine.repository"})
@SpringBootApplication
@EnableJpaRepositories("com.valentine.repository")
public class AdminPortalApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;


    @Autowired
    private SecurityUtility securityUtility;

    public static void main(String[] args) {
        SpringApplication.run(AdminPortalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User();
        user1.setUsername("admin");
        user1.setFirstName("val");
        user1.setPassword(securityUtility.passwordEncoder().encode("admin"));
        user1.setEmail("admin@yahoo.com");
        Set<UserRole> userRoles = new HashSet<>();
        Role role1 = new Role();
        role1.setRoleId(0);
        role1.setName("ADMIN");
        userRoles.add(new UserRole(user1, role1));
        userService.createUser(user1, userRoles);
    }
}
