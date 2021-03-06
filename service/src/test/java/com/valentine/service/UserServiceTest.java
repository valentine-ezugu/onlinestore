package com.valentine.service;

import com.valentine.domain.*;
import com.valentine.repository.PasswordResetTokenRepository;
import com.valentine.repository.UserPaymentRepository;
import com.valentine.repository.UserRepository;
import com.valentine.repository.UserShippingRepository;
import com.valentine.utility.SecurityUtility;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {UserServiceImpl.class, SecurityUtility.class, UserRepository.class})
//because we need more than one class context but yet not all app context
public class UserServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityUtility securityUtility;

    @MockBean
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserPaymentRepository userPaymentRepository;

    @MockBean
    private UserShippingRepository userShippingRepository;


    @Test
    public void saveUser() throws Exception {
        User user = new User();
        user.setUsername("val");

        user.setPassword(securityUtility.passwordEncoder().encode("vsl"));
        user.setEmail("val@yahoo.com");

        Set<Role> roles = new HashSet<>();

        user.setRoles(roles);
        when(userRepository.save(user)).thenReturn(user);

        User user1 = userService.save(user);
        Assert.assertNotNull(user1);
        Assert.assertEquals("val", user1.getUsername());
    }

    @Test
    public void createUserTest() throws Exception {

        User user = new User();
        user.setUsername("valentine");
        when(userRepository.findByUsername("valentine")).thenReturn(user);

        User user1 = userService.findByUsername("valentine");

        Assert.assertEquals("valentine", user1.getUsername());
        user1.setFirstName("Ezugu");

        if (!user1.equals(null)) {
            logger.info("do nothing ");
        } else {
            when(userRepository.save(user1)).thenReturn(user1);
            User userCreated = userService.createUser(user1);

            Assert.assertNotNull(userCreated);
            Assert.assertEquals("Ezugu", userCreated.getFirstName());

        }
    }

    @Test
    public void findByEmailTest() throws Exception {
        User user = new User();
        user.setEmail("val@yahoo.com");
        when(userRepository.findByEmail("val@yahoo.com")).thenReturn(user);

        User foundUser = userService.findByEmail("val@yahoo.com");

        Assert.assertNotNull(foundUser);
        Assert.assertEquals("val@yahoo.com", foundUser.getEmail());
        Mockito.verify(userRepository).findByEmail("val@yahoo.com");
    }


    @Test
    public void getUserByIdTest() throws Exception {
        User user = new User();

        when(userRepository.findOne(1L)).thenReturn(user);
        User foundUser = userService.findById(1L);

        Assert.assertNotNull(foundUser);
        Mockito.verify(userRepository).findOne(1L);
    }

    @Test
    public void getPasswordResetTokenTest() throws Exception {

        PasswordResetToken passwordResetToken = new PasswordResetToken();
        final String token = "myToken";
        when(passwordResetTokenRepository.findByToken(token)).thenReturn(passwordResetToken);

        PasswordResetToken prt = userService.getPasswordResetToken(token);
        Assert.assertNotNull(prt);
        Mockito.verify(passwordResetTokenRepository).findByToken(token);
    }

    @Test
    public void createPasswordResetTokenForUserTest() throws Exception {
        User user = new User();

        String token = UUID.randomUUID().toString();
        PasswordResetToken myToken = new PasswordResetToken(token, user);

        passwordResetTokenRepository.save(myToken);

        userService.createPasswordResetTokenForUser(user, token);
        Mockito.verify(passwordResetTokenRepository).save(myToken);
    }


    @Test
    public void findByUsername() throws Exception {
        User user = new User();
        user.setUsername("teacher");
        when(userRepository.findByUsername("teacher")).thenReturn(user);

        User foundUser = userService.findByUsername("teacher");
        Assert.assertNotNull(foundUser);

        Assert.assertEquals("teacher", foundUser.getUsername());
        Mockito.verify(userRepository).findByUsername("teacher");
    }


    @Test
    public void setUserDefaultShippingTest() throws Exception {

        Long id = 2L;
        User user = new User();
        UserShipping userShipping = new UserShipping();
        userShipping.setId(1L);

        UserShipping userShipping1 = new UserShipping();
        userShipping1.setId(2L);

        List<UserShipping> userShippingList = Arrays.asList(userShipping, userShipping1);
        user.setUserShippingList(userShippingList);

        when(userShippingRepository.findAll()).thenReturn(userShippingList);
        for (UserShipping userShipping3 : userShippingList) {

            if (userShipping3.getId().equals(id)) {
                userShipping3.setUserShippingDefault(true);

                userShippingRepository.save(userShipping3);
                Mockito.verify(userShippingRepository).findAll();
            } else {

                userShipping3.setUserShippingDefault(false);
                userShippingRepository.save(userShipping3);
                Mockito.verify(userShippingRepository).save(userShipping3);
            }
            userService.setUserDefaultShipping(id, user);
        }
    }

    @Test
    public void payment() throws Exception {
        Long id = 2L;
        User user = new User();
        UserPayment userPayment = new UserPayment();
        userPayment.setId(1L);

        UserPayment userPayment1 = new UserPayment();
        userPayment1.setId(2L);

        List<UserPayment> userPaymentList = Arrays.asList(userPayment, userPayment1);
        user.setUserPaymentList(userPaymentList);

        when(userPaymentRepository.findAll()).thenReturn(userPaymentList);
        for (UserPayment userPayment2 : userPaymentList) {

            if (userPayment2.getId().equals(id)) {
                userPayment2.setDefaultPayment(true);
                userPaymentRepository.save(userPayment2);
                Mockito.verify(userPaymentRepository).findAll();
            } else {

                userPayment2.setDefaultPayment(false);
                userPaymentRepository.save(userPayment2);
                Mockito.verify(userPaymentRepository).save(userPayment2);
            }
            userService.setUserDefaultPayment(id, user);
        }
    }
}


