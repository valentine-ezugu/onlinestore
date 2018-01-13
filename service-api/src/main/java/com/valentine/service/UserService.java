package com.valentine.service;

import com.valentine.domain.User;
import com.valentine.domain.UserBilling;
import com.valentine.domain.UserPayment;
import com.valentine.domain.UserShipping;
import com.valentine.domain.security.PasswordResetToken;
import com.valentine.domain.security.UserRole;
import org.springframework.dao.DataAccessException;

import java.util.Set;

public interface UserService {

    PasswordResetToken getPasswordResetToken(final String token);

    void createPasswordResetTokenForUser(final User user, final String token);

    User findByUsername(String username) throws DataAccessException;

    User findById(Long id) throws DataAccessException;

    User getUserById(Long id) throws DataAccessException;

    User findByEmail(String email) throws DataAccessException;


    User createUser(User user, Set<UserRole> userRoles) throws Exception;

    User save(User user);

    void setUserDefaultShipping(Long userShippingId, User user) throws DataAccessException;

    void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user) throws DataAccessException;

    void setUserDefaultPayment(Long userPaymentId, User user) throws DataAccessException;

    void updateUserShipping(UserShipping userShipping, User user) throws DataAccessException;

}

