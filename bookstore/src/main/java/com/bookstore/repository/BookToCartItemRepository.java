package com.bookstore.repository;
import com.bookstore.domain.*;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BookToCartItemRepository extends CrudRepository<BookToCartItem, Long > {

    void deleteByCartItem(CartItem cartItem);
}
