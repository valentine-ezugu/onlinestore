package com.bookstore.services.api;


import com.domain.domain.*;
import org.springframework.dao.DataAccessException;

public interface ShippingAddressService {
    ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress) throws DataAccessException;
}
