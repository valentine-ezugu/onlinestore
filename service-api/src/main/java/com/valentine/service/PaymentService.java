package com.valentine.service;

import com.valentine.domain.Payment;
import com.valentine.domain.UserPayment;
import org.springframework.dao.DataAccessException;

public interface PaymentService {
    Payment setByUserPayment(UserPayment userPayment, Payment payment) throws DataAccessException;
}
