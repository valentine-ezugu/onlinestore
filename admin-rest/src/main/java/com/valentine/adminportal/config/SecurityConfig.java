package com.valentine.adminportal.config;

import com.valentine.service.UserDetailsServiceImpl;
import com.valentine.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *This clas handles creation of security for the web app
 * has some config that explains the process of receiving request to reponse
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] PUBLIC_MATCHERS = {
            "/css/**",
            "/js/**",
            "/image/**",
            "/newUser",
            "/ForgetPassword",
            "/login",
            "/fonts/**",
            "/bookshelf"
    };
    /**
     *bean that contains password encoder
     */
    @Autowired
    private SecurityUtility securityUtility;

    /**
     *used for receiving username for
     * authentication and provides
     * verification of passowrd and authorities
     */
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    /**
     *
     * @return bean that hashes password
     */
    private BCryptPasswordEncoder passwordEncoder() {
        return securityUtility.passwordEncoder();
    }

    /**
     *
     * @param http takes method http as param
     * @throws Exception is thrown when a logic or request cannot go through
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().cors().disable()
                .authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .antMatchers("/book/remove/**").hasAuthority("ADMIN")
                .antMatchers("/book/bookList/**").hasAuthority("ADMIN")
                .antMatchers("/book/add/**").hasAuthority("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .failureUrl("/login?error")
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/?logout").deleteCookies("remember-me").permitAll()
                .and()
                .rememberMe();
    }

    /**
     *
     * @param auth of password and and username
     * @throws Exception when authority fails
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

}