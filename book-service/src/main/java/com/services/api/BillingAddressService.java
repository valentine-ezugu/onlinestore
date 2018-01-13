package com.services.api;


import com.domain.domain.BillingAddress;
import com.domain.domain.UserBilling;
import org.springframework.dao.DataAccessException;

public interface BillingAddressService {
    BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress) throws DataAccessException;
}
