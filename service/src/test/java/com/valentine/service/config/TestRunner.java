package com.valentine.service.config;

import com.valentine.repository.RoleRepository;
import com.valentine.utility.SecurityUtility;
import com.valentine.domain.User;
import com.valentine.domain.Role;
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
@EntityScan(basePackages = {"com.valentine.domain"})
@ComponentScan(basePackages = {
        "com.valentine"})
@SpringBootApplication
@EnableJpaRepositories("com.valentine.repository")
public class TestRunner implements CommandLineRunner {

    @Autowired
    private  RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityUtility securityUtility;

    public static void main(String[] args) {
        SpringApplication.run(TestRunner.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User();
        user1.setUsername("admin");
        user1.setFirstName("val");
        user1.setPassword(securityUtility.passwordEncoder().encode("admin"));
        user1.setEmail("admin@yahoo.com");
        Role role1 = new Role();
        Set<Role> roles = new HashSet<>();
        role1 = roleRepository.findByname("USER");
        roles.add(role1);
        user1.setRoles(roles);
        userService.createUser(user1);
    }
}
