package com.services.api;


import com.domain.domain.ShippingAddress;
import com.domain.domain.UserShipping;
import org.springframework.dao.DataAccessException;

public interface ShippingAddressService {
    ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress) throws DataAccessException;
}
