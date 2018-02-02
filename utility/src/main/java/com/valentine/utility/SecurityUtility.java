package com.valentine.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class SecurityUtility {

    private final String SALT = "salt";

    private Random random;

    @Autowired
    public SecurityUtility(@Value("#{new java.util.Random()}") Random random) {
        this.random = random;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
    }

    public String randomPassword() {
        String saltChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        StringBuilder sb = new StringBuilder();

        while (sb.length() < 18) {
            int index = random.nextInt(saltChars.length());
            sb.append(saltChars.charAt(index));
        }
        return sb.toString();
    }

}

