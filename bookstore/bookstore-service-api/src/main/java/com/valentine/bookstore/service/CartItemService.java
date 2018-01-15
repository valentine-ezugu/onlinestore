package com.valentine.bookstore.service;

import com.valentine.domain.*;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Created by Pc on 9/8/2017.
 */
public interface CartItemService {
    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) throws DataAccessException;

    CartItem updateCartItem(CartItem cartItem) throws DataAccessException;

    CartItem addBookToCartItem(Book book, User user, int qty) throws DataAccessException;

    CartItem findById(Long id) throws DataAccessException;

    void removeCartItem(CartItem cartItem) throws DataAccessException;

    CartItem save(CartItem cartItem) throws DataAccessException;

    List<CartItem> findByOrder(Order order) throws DataAccessException;
}
