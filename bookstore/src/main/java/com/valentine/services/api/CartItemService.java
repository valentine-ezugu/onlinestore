package com.valentine.services.api;

import com.domain.domain.ShoppingCart;
import com.domain.domain.*;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface CartItemService {
    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) throws DataAccessException;

    CartItem updateCartItem(CartItem cartItem) throws DataAccessException;

    CartItem addBookToCartItem(Book book, User user, int qty) throws DataAccessException;

    CartItem findById(Long id) throws DataAccessException;

    void removeCartItem(CartItem cartItem) throws DataAccessException;

    CartItem save(CartItem cartItem) throws DataAccessException;

    List<CartItem> findByOrder(Order order) throws DataAccessException;
}
