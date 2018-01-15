package com.valentine.bookstore.service;


import com.bookstore.domain.BillingAddress;
import com.bookstore.domain.UserBilling;
import com.bookstore.services.api.BillingAddressService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BillingAddressServiceImpl implements BillingAddressService {

    public BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress) throws DataAccessException

    {
        billingAddress.setBillingAddressName(userBilling.getUserBillingName());
        billingAddress.setBillingAddressStreet1(userBilling.getUserBillingStreet1());
        billingAddress.setBillingAddressStreet2(userBilling.getUserBillingStreet2());
        billingAddress.setBillingAddressCity(userBilling.getUserBillingCity());
        billingAddress.setBillingAddressState(userBilling.getUserBillingState());
        billingAddress.setBillingAddressCountry(userBilling.getUserBillingCountry());
        billingAddress.setBillingAddressZipCode(userBilling.getUserBillingZipCode());

        return billingAddress;
    }

}
