package com.valentine.repository;

import com.valentine.domain.BookToCartItem;
import com.valentine.domain.CartItem;
import org.springframework.data.repository.CrudRepository;


public interface BookToCartItemRepository extends CrudRepository<BookToCartItem, Long> {

    void deleteByCartItem(CartItem cartItem);
}
