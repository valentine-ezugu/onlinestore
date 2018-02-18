package com.valentine.service;

import com.valentine.domain.Book;
import com.valentine.domain.CartItem;
import com.valentine.domain.ShippingAddress;
import com.valentine.domain.ShoppingCart;
import com.valentine.repository.CartItemRepository;
import com.valentine.repository.ShoppingCartRepository;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingCartServiceImpl.class)
public class ShoppingCartServiceTest {

    @MockBean
    private CartItemService cartItemService;

    @MockBean
    private CartItemRepository cartItemRepository;

    @MockBean
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Rule
    public final ExpectedException exception = ExpectedException.none();


    @Test
    public void updateShoppingCartTest() throws Exception {

        ShoppingCart shoppingCart = new ShoppingCart();

        Book book = new Book();
        book.setInStockNumber(10);

        Book book1 = new Book();
        book1.setInStockNumber(10);

        BigDecimal cartTotal = new BigDecimal(0);

        CartItem cartItem1 = new CartItem();
        cartItem1.setBook(book);
        cartItem1.setSubTotal(cartTotal);

        CartItem cartItem2 = new CartItem();
        cartItem2.setBook(book1);
        cartItem2.setSubTotal(cartTotal);

        List<CartItem> cartItemList = Arrays.asList(cartItem1, cartItem2);

        when(cartItemRepository.findByShoppingCart(shoppingCart)).thenReturn(cartItemList);

        for (CartItem cartItem : cartItemList) {

            if (cartItem.getBook().getInStockNumber() > 0) {
                cartTotal = cartTotal.add(cartItem.getSubTotal());
            }
        }

        shoppingCart.setGrandTotal(cartTotal);
        when(shoppingCartRepository.save(shoppingCart)).thenReturn(shoppingCart);

        ShoppingCart cart = shoppingCartService.updateShoppingCart(shoppingCart);

        Assert.assertNotNull(cart);
        Mockito.verify(cartItemRepository).findByShoppingCart(shoppingCart);
        Mockito.verify(shoppingCartRepository).save(shoppingCart);
    }

    @Test
    public void clearShoppingCartTest() throws Exception {
        CartItem cartItem = new CartItem();
        CartItem cartItem1 = new CartItem();
        ShoppingCart shoppingCart = new ShoppingCart();

        shoppingCart.setCartItemList(Arrays.asList(cartItem, cartItem1));

        List<CartItem> cartItemList = cartItemRepository.findByShoppingCart(shoppingCart);

        for (CartItem cartItem3 : cartItemList) {
            cartItem3.setShoppingCart(null);
            cartItemService.save(cartItem3);
        }
        shoppingCart.setGrandTotal(new BigDecimal(0));

        when(shoppingCartRepository.save(shoppingCart)).thenReturn(shoppingCart);

        shoppingCartService.clearShoppingCart(shoppingCart);

        Mockito.verify(shoppingCartRepository).save(shoppingCart);
    }

    @Test
    public void exceptionTestForUpdate() throws Exception {
        ShoppingCart shoppingCart = new ShoppingCart();

        //because to save shopping cart first we need to find there fore null pointer
        when(shoppingCartRepository.save(shoppingCart)).thenThrow(new NullPointerException("") {
        });

        exception.expect(NullPointerException.class);
        shoppingCartService.updateShoppingCart(shoppingCart);
    }

    @Test
    public void exceptionForClearShopping() throws Exception {

        ShoppingCart shoppingCart = new ShoppingCart();
        when(shoppingCartRepository.save(shoppingCart)).thenThrow(new NullPointerException("") {
        });

        exception.expect(NullPointerException.class);
        shoppingCartService.clearShoppingCart(shoppingCart);
    }

}
