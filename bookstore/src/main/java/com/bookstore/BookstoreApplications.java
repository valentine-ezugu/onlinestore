package com.bookstore;

import com.bookstore.domain.*;
import com.bookstore.domain.security.Role;
import com.bookstore.domain.security.UserRole;
import com.bookstore.services.api.UserService;
import com.bookstore.utility.SecurityUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashSet;
import java.util.Set;

@Configuration
@SpringBootApplication
@EntityScan(basePackages = {"com.bookstore"})
@EnableJpaRepositories(basePackages = {"com.bookstore.repository"})
 @EnableTransactionManagement
public class BookstoreApplications  implements CommandLineRunner   {

    @Autowired
    private SecurityUtility securityUtility;

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(BookstoreApplications.class);


    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplications.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        User user1 = new User();
        user1.setFirstname("Valentine");
        user1.setLastname("Ezugu");
        user1.setUsername("V");
        user1.setPassword(securityUtility.passwordEncoder().encode("A"));
        user1.setEmail("valentineezugu@yahoo.com");
        Set<UserRole> userRoles = new HashSet<>();
        Role role1 = new Role();
        role1.setRoleId(1);
        role1.setName("USER");
        userRoles.add(new UserRole(user1, role1));
        userService.createUser(user1, userRoles);

        logger.error("Message logged at ERROR level");
        logger.warn("Message logged at WARN level");
        logger.info("Message logged at INFO level");
        logger.debug("Message logged at DEBUG level");
    }
}