package com.bookstore.services.impl;

import com.bookstore.services.api.LoginService;
import com.bookstore.domain.security.LoginStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SpringSecurityLoginService implements LoginService {
    private Log log = LogFactory.getLog(SpringSecurityLoginService.class);

    @Autowired
    public AuthenticationManager authenticationManager;

    public LoginStatus getStatus()throws DataAccessException

    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && !auth.getName().equals("anonymousUser") && auth.isAuthenticated()) {
            return new LoginStatus(true, auth.getName());
        } else {
            return new LoginStatus(false, null);
        }
    }

    public LoginStatus login(String username, String password)throws DataAccessException, AccessDeniedException
    {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        try {
            Authentication auth = authenticationManager.authenticate(token);
            log.debug("Login succeeded!");
            SecurityContextHolder.getContext().setAuthentication(auth);

            return new LoginStatus(auth.isAuthenticated(), auth.getName());
        } catch (BadCredentialsException e) {
            return new LoginStatus(false, null);
        }
    }
}
