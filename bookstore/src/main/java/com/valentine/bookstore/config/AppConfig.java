package com.valentine.bookstore.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.bookstore","com.domain", "com.valentine","com.valentine"})
@Import({SecurityConfig.class})
public class AppConfig {
}
