package com.valentine.services.api;


import org.springframework.dao.DataAccessException;
import com.domain.domain.*;
public interface BillingAddressService {
    BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress) throws DataAccessException;
}
