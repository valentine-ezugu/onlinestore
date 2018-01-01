package com.bookstore.services.api;

import com.bookstore.domain.*;

import org.springframework.dao.DataAccessException;

public interface PaymentService {
    Payment setByUserPayment(UserPayment userPayment, Payment payment) throws DataAccessException;
}
