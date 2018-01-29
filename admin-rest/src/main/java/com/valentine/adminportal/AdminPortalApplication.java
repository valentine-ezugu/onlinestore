package com.valentine.adminportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EntityScan(basePackages = {"com.valentine.domain"})
@SpringBootApplication(scanBasePackages = {"com.valentine.adminportal", "com.valentine.service", "com.valentine.utility"})
@EnableJpaRepositories("com.valentine.repository")
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AdminPortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminPortalApplication.class, args);
    }
}


