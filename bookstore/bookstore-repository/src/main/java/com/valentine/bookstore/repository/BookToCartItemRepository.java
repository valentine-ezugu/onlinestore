package com.valentine.bookstore.repository;

import com.valentine.domain.BookToCartItem;
import com.valentine.domain.CartItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BookToCartItemRepository extends CrudRepository<BookToCartItem, Long> {

    void deleteByCartItem(CartItem cartItem);
}
