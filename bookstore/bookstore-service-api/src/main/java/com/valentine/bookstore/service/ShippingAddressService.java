package com.valentine.bookstore.service;

import com.valentine.domain.ShippingAddress;
import com.valentine.domain.UserShipping;
import org.springframework.dao.DataAccessException;

public interface ShippingAddressService {
    ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress) throws DataAccessException;
}
