package com.data.persistence;
import com.domain.domain.BookToCartItem;
import com.domain.domain.CartItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BookToCartItemRepository extends CrudRepository<BookToCartItem, Long> {

    void deleteByCartItem(CartItem cartItem);
}