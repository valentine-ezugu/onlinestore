package com.valentine.repository;

import com.valentine.domain.CartItem;
import com.valentine.domain.Order;
import com.valentine.domain.ShoppingCart;
import org.springframework.data.repository.CrudRepository;


import java.util.List;


public interface CartItemRepository extends CrudRepository<CartItem, Long> {

    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);

    List<CartItem> findByOrder(Order order);
}
