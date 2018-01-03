package com.adminportal;

import com.adminportal.domain.User;
import com.adminportal.domain.security.Role;
import com.adminportal.domain.security.UserRole;
import com.adminportal.dto.user.LoginInfo;
import com.adminportal.service.api.UserService;
import com.adminportal.utility.SecurityUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashSet;
import java.util.Set;

@Configuration
@SpringBootApplication
@EnableTransactionManagement
public class AdminPortalApplication implements CommandLineRunner {

    @Autowired
    private SecurityUtility securityUtility;

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(AdminPortalApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AdminPortalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        LoginInfo loginDto = new LoginInfo();
        loginDto.setPassword(securityUtility.passwordEncoder().encode("admin"));
        loginDto.setUsername("admin");
        User user1 = new User(loginDto);
        user1.setEmail("admin@yahoo.com");
        Set<UserRole> userRoles = new HashSet<>();
        Role role1 = new Role();
        role1.setRoleId(0);
        role1.setName("ADMIN");
        userRoles.add(new UserRole(user1, role1));
        userService.createUser(user1, userRoles);

        logger.error("Message logged at ERROR level");
        logger.warn("Message logged at WARN level");
        logger.info("Message logged at INFO level");
        logger.debug("Message logged at DEBUG level");
    }

}
