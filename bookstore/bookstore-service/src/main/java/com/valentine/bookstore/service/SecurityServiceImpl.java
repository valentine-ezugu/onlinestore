package com.valentine.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SecurityServiceImpl implements SecurityService {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    public String findLoggedInUsername() throws DataAccessException, AuthenticationException {


        SecurityContext securityContext = SecurityContextHolder.getContext();


        Authentication authentication = securityContext.getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken) {

            throw new UsernameNotFoundException("sorry but you are Anonymous");

        }


        String currentUserName = authentication.getName();

        return currentUserName;

    }


    @Override
    public void autologin(String username, String password) throws AuthenticationException {


        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

            authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        } catch (Exception ex) {

            throw new AuthenticationServiceException("invalid username or password");
        }
    }
}