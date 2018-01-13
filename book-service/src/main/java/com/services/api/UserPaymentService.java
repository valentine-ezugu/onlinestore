package com.services.api;

import com.domain.domain.UserPayment;
import org.springframework.dao.DataAccessException;

/**
 * Created by Pc on 8/26/2017.
 */
public interface UserPaymentService {

    UserPayment findById(Long id) throws DataAccessException;

    void removeById(Long id) throws DataAccessException;
}
