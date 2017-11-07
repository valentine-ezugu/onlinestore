package com.bookstore.service.api;

import com.bookstore.domain.User;
import com.bookstore.domain.UserBilling;
import com.bookstore.domain.UserPayment;
import com.bookstore.domain.UserShipping;
import com.bookstore.domain.security.PasswordResetToken;
import com.bookstore.domain.security.UserRole;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Set;

public interface UserService {

    PasswordResetToken getPasswordResetToken(final String token);

    void createPasswordResetTokenForUser(final User user, final String token);

    User findByUsername(String username)throws DataAccessException ;

    User findById(Long id) throws DataAccessException ;

    User getUserById(Long id)throws DataAccessException;

    User findByEmail(String email) throws DataAccessException;


    User createUser(User user, Set<UserRole> userRoles)throws Exception;

    User save(User user);

    void setUserDefaultShipping(Long userShippingId, User user)throws DataAccessException;

    void  updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user)throws DataAccessException;

    void setUserDefaultPayment(Long userPaymentId, User user)throws DataAccessException;

    void updateUserShipping(UserShipping userShipping, User user) throws DataAccessException;

}

