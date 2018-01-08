package com.bookstore.services.api;

import com.bookstore.domain.BillingAddress;
import com.bookstore.domain.UserBilling;
import org.springframework.dao.DataAccessException;

public interface BillingAddressService {
    BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress) throws DataAccessException;
}
