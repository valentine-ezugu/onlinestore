package com.bookstore.service.impl;

import com.bookstore.domain.*;
import com.bookstore.repository.BookToCartItemRepository;
import com.bookstore.repository.CartItemRepository;
import com.bookstore.service.api.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Pc on 9/8/2017.
 */
@Service
@Transactional
public class CartItemServiceImpl implements CartItemService {

    @Override
    public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) {
        return cartItemRepository.findByShoppingCart(shoppingCart);
    }

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private BookToCartItemRepository bookToCartItemRepository;

    public CartItem updateCartItem(CartItem cartItem) throws AccessDeniedException , DataAccessException
    {
        BigDecimal bigDecimal = new BigDecimal(cartItem.getBook().getOurPrice()).multiply(new BigDecimal(cartItem.getQty()));

        bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        cartItem.setSubTotal(bigDecimal);

        cartItemRepository.save(cartItem);

        return cartItem;
    }

     public CartItem addBookToCartItem(Book book, User user, int qty) throws   AccessDeniedException
    {
        List<CartItem> cartItemList = findByShoppingCart(user.getShoppingCart());

        for (CartItem cartItem : cartItemList) {
            if(book.getId() == cartItem.getBook().getId()) {
                cartItem.setQty(cartItem.getQty()+qty);
                cartItem.setSubTotal(new BigDecimal(book.getOurPrice()).multiply(new BigDecimal(qty)));
                cartItemRepository.save(cartItem);
                return cartItem;
            }
        }

        CartItem cartItem = new CartItem();
        cartItem.setShoppingCart(user.getShoppingCart());
        cartItem.setBook(book);

        cartItem.setQty(qty);
        cartItem.setSubTotal(new BigDecimal(book.getOurPrice()).multiply(new BigDecimal(qty)));
        cartItem = cartItemRepository.save(cartItem);

        BookToCartItem bookToCartItem = new BookToCartItem();
        bookToCartItem.setBook(book);
        bookToCartItem.setCartItem(cartItem);
        bookToCartItemRepository.save(bookToCartItem);

        return cartItem;
    }


    public CartItem findById(Long id)throws DataAccessException, AccessDeniedException
    {
        return cartItemRepository.findOne(id);
    }

    public void removeCartItem(CartItem cartItem) throws DataAccessException

    {
        bookToCartItemRepository.deleteByCartItem(cartItem);
        cartItemRepository.delete(cartItem);
    }


    public CartItem save(CartItem cartItem)throws DataAccessException, AccessDeniedException
    {
        return cartItemRepository.save(cartItem);
    }

    public List<CartItem> findByOrder(Order order)throws DataAccessException, AccessDeniedException
    {
        return  cartItemRepository.findByOrder(order);

    }
}
