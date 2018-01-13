package com.valentine.services.api;

import com.domain.domain.*;

import org.springframework.dao.DataAccessException;

public interface PaymentService {
    Payment setByUserPayment(UserPayment userPayment, Payment payment) throws DataAccessException;
}
