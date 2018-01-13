package com.services.api;

import com.domain.domain.Payment;
import com.domain.domain.UserPayment;
import org.springframework.dao.DataAccessException;

public interface PaymentService {
    Payment setByUserPayment(UserPayment userPayment, Payment payment) throws DataAccessException;
}
