package com.valentine.bookstore;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EntityScan(basePackages = {"com.valentine.domain"})
@SpringBootApplication(scanBasePackages = {"com.valentine.bookstore", "com.valentine.service", "com.valentine.utility"})
@EnableJpaRepositories("com.valentine.repository")
@EnableTransactionManagement
public class BookstoreApplications {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplications.class, args);
    }

}