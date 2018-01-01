package com.bookstore.services.api;


import com.bookstore.domain.*;
import org.springframework.dao.DataAccessException;


public interface ShoppingCartService {

  ShoppingCart updateShoppingCart(ShoppingCart shoppingCart)  throws DataAccessException;

 void clearShoppingCart(ShoppingCart shoppingCart) throws DataAccessException;
}
