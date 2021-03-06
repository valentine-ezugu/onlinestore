package com.valentine.service;


import com.valentine.domain.ShoppingCart;
import org.springframework.dao.DataAccessException;


public interface ShoppingCartService {

    ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) throws DataAccessException;

    void clearShoppingCart(ShoppingCart shoppingCart) throws DataAccessException;
}
