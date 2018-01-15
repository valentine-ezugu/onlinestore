package com.valentine.adminportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"com.valentine.domain"})
@ComponentScan(basePackages = {"com.valentine.adminportal", "com.valentine.common.service", "com.valentine.common.utility"})
@SpringBootApplication
@EnableJpaRepositories("com.valentine.common.repository")
public class AdminPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminPortalApplication.class, args);
    }

    //It have to be in sql scripts
//    @Override
//    public void run(String... args) throws Exception {
//        User user1 = new User();
//        user1.setUsername("admin");
//        user1.setFirstName("val");
//        user1.setPassword(securityUtility.passwordEncoder().encode("admin"));
//        user1.setEmail("admin@yahoo.com");
//        Set<UserRole> userRoles = new HashSet<>();
//        Role role1 = new Role();
//        role1.setRoleId(0);
//        role1.setName("ADMIN");
//        userRoles.add(new UserRole(user1, role1));
//        userService.createUser(user1, userRoles);
//    }
}
