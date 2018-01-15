package com.valentine.bookstore.service;

import com.bookstore.domain.Payment;
import com.bookstore.domain.UserPayment;
import org.springframework.dao.DataAccessException;

public interface PaymentService {
    Payment setByUserPayment(UserPayment userPayment, Payment payment) throws DataAccessException;
}
