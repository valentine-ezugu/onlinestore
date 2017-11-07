package com.bookstore.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
@Configuration
@ComponentScan("com.bookstore")
@Import({SecurityConfig.class })
public class AppConfig {
}
