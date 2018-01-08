package com.bookstore.services.api;

import com.bookstore.domain.ShippingAddress;
import com.bookstore.domain.UserShipping;
import org.springframework.dao.DataAccessException;

public interface ShippingAddressService {
    ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress) throws DataAccessException;
}
