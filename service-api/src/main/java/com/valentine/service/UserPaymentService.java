package com.valentine.service;

import com.valentine.domain.UserPayment;
import org.springframework.dao.DataAccessException;

public interface UserPaymentService {

    UserPayment findById(Long id) throws DataAccessException;

    void removeById(Long id) throws DataAccessException;
}
