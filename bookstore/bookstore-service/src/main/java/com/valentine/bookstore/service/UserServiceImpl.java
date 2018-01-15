package com.valentine.bookstore.service;

import com.valentine.bookstore.repository.*;
import com.valentine.common.repository.UserRepository;
import com.valentine.domain.*;
import com.valentine.domain.security.PasswordResetToken;
import com.valentine.domain.security.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@javax.transaction.Transactional
public class UserServiceImpl implements UserService {

    private static final Logger Log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPaymentRepository userPaymentRepository;

    @Autowired
    private UserShippingRepository userShippingRepository;

    @Override
    public PasswordResetToken getPasswordResetToken(final String token) throws DataAccessException {
        return passwordResetTokenRepository.findByToken(token);
    }

    @Override
    public void createPasswordResetTokenForUser(final User user, final String token) throws DataAccessException {
        final PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(myToken);
    }

    @Override
    public User findByUsername(String username) throws DataAccessException {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(Long id) throws DataAccessException {
        return userRepository.findOne(id);
    }

    @Override
    public User getUserById(Long id) throws DataAccessException {
        return userRepository.findOne(id);
    }

    @Override
    public User findByEmail(String email) throws DataAccessException {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public User createUser(User user, Set<UserRole> userRoles) throws DataAccessException {
        User localUser = userRepository.findByUsername(user.getUsername());

        if (localUser != null) {
            Log.info("user {} already exists. Nothing will be done", user.getUsername());
        } else {
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);

            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setUser(user);
            user.setShoppingCart(shoppingCart);

            user.setUserShippingList(new ArrayList<UserShipping>());
            user.setUserPaymentList(new ArrayList<UserPayment>());

            localUser = userRepository.save(user);
        }
        return localUser;
    }

    @Override
    public User save(User user) throws DataAccessException {
        return userRepository.save(user);
    }

    @Override
    public void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user) {

        userPayment.setUser(user);
        userPayment.setUserBilling(userBilling);
        userPayment.setDefaultPayment(true);
        userBilling.setUserPayment(userPayment);
        user.getUserPaymentList().add(userPayment);
        save(user);

    }

    @Override
    public void updateUserShipping(UserShipping userShipping, User user) throws DataAccessException {
        userShipping.setUser(user);
        userShipping.setUserShippingDefault(true);
        user.getUserShippingList().add(userShipping);
        save(user);
    }

    @Override
    public void setUserDefaultShipping(Long userShippingId, User user) throws DataAccessException {
        List<UserShipping> userShippingList = (List<UserShipping>) userShippingRepository.findAll();

        for (UserShipping userShipping : userShippingList) { //first we look through shipping list if any
            if (userShipping.getId() == userShippingId) {
                userShipping.setUserShippingDefault(true);
                userShippingRepository.save(userShipping);
            } else {
                userShipping.setUserShippingDefault(false);
                userShippingRepository.save(userShipping);
            }
        }
    }


    @Override
    public void setUserDefaultPayment(Long userPaymentId, User user) throws DataAccessException {

        List<UserPayment> userPaymentsList = (List<UserPayment>) userPaymentRepository.findAll();

        for (UserPayment userPayment : userPaymentsList) {
            if (userPayment.getId() == userPaymentId) {
                userPayment.setDefaultPayment(true);
                userPaymentRepository.save(userPayment);
            } else {
                userPayment.setDefaultPayment(false);
                userPaymentRepository.save(userPayment);
            }
        }
    }
}
