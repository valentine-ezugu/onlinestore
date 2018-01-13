package com.valentine.test.service;

import com.valentine.domain.security.LoginStatus;
import com.valentine.service.SpringSecurityLoginService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.transaction.Transactional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SpringSecurityLoginTest {

    @Autowired
    private SpringSecurityLoginService loginService;

    private AuthenticationManager authenticationManager;

    @Before
    public void before() {
        authenticationManager = mock(AuthenticationManager.class);
        ReflectionTestUtils.setField(loginService, "authenticationManager", authenticationManager);
    }

    @After
    public void after() {
        SecurityContextHolder.clearContext();
    }

    @Test
    public void testLoginStatusSuccess() {
        Authentication auth = new TestingAuthenticationToken("foo", "bar");
        auth.setAuthenticated(true);
        SecurityContext context = new SecurityContextImpl();
        context.setAuthentication(auth);
        SecurityContextHolder.setContext(context);

        LoginStatus status = loginService.getStatus();
        assertTrue(status.isLoggedIn());
    }

    @Test
    public void testLoginStatusFailure() {
        LoginStatus status = loginService.getStatus();
        assertFalse(status.isLoggedIn());
    }

    @Test
    public void testGoodLogin() {
        Authentication auth = new TestingAuthenticationToken("foo", "bar");
        auth.setAuthenticated(true);
        when(authenticationManager.authenticate(Matchers.<Authentication>anyObject())).thenReturn(auth);
        LoginStatus status = loginService.login("foo", "bar");
        assertTrue(status.isLoggedIn());
        assertEquals("foo", status.getUsername());
    }

    @Test
    public void testBadLogin() {
        Authentication auth = new TestingAuthenticationToken("foo", "bar");
        auth.setAuthenticated(false);
        when(authenticationManager.authenticate(Matchers.<Authentication>anyObject()))
                .thenThrow(new BadCredentialsException("Bad Credentials"));
        LoginStatus status = loginService.login("foo", "bar");
        assertFalse(status.isLoggedIn());
        assertEquals(null, status.getUsername());
    }
}

