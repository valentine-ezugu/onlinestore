package com.valentine.service;

import com.valentine.domain.*;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


@Service
public interface OrderService {

    Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress, BillingAddress billingAddress, Payment payment, String shippingMethod, User user) throws DataAccessException;

    Order findOne(Long id) throws DataAccessException;

}
