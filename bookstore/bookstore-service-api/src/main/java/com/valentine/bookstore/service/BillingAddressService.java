package com.valentine.bookstore.service;

import com.valentine.domain.BillingAddress;
import com.valentine.domain.UserBilling;
import org.springframework.dao.DataAccessException;

public interface BillingAddressService {
    BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress) throws DataAccessException;
}
