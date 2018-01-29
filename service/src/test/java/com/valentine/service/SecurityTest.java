package com.valentine.service;

import com.valentine.utility.LoginStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.transaction.Transactional;

import static org.easymock.EasyMock.mock;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration
public class SecurityTest extends AbstractTest {

    @Autowired
    private LoginService loginService;

    @Autowired
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

}

