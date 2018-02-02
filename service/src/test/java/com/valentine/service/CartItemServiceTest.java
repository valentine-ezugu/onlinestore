package com.valentine.service;

import com.valentine.domain.*;
import com.valentine.repository.BookToCartItemRepository;
import com.valentine.repository.CartItemRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CartItemServiceImpl.class)
public class CartItemServiceTest {

    @MockBean
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartItemService cartItemService;

    @MockBean
    private SecurityService securityService;

    @MockBean
    private BookToCartItemRepository bookToCartItemRepository;


    @Test
    public void findByShoppingCartTest() throws Exception {
        CartItem cartItem = new CartItem();
        CartItem cartItem1 = new CartItem();

        ShoppingCart myCart = new ShoppingCart();

        when(cartItemRepository.findByShoppingCart(myCart)).thenReturn(Arrays.asList(cartItem, cartItem1));

        List<CartItem> cartItemList = cartItemService.findByShoppingCart(myCart);

        Assert.assertNotNull(cartItemList);
        Assert.assertEquals(2, cartItemList.size());
        Mockito.verify(cartItemRepository).findByShoppingCart(myCart);
    }

    @Test
    public void addBookToCartItemTest() throws Exception {
        Book book1 = new Book();
        User user = new User();
        ShoppingCart shoppingCart = new ShoppingCart();

        CartItem cartItem1 = new CartItem();
         cartItem1.setBook(book1);
        cartItem1.setQty(2);

        CartItem cartItem = new CartItem();
        cartItem.setBook(book1);
        cartItem.setQty(2);

        int qty = 2;

        List<CartItem> cartItemList = Arrays.asList(cartItem, cartItem1);

        when(cartItemRepository.findByShoppingCart(user.getShoppingCart())).thenReturn(cartItemList);

        for (CartItem item : cartItemList) {

            if (book1.getId() == item.getBook().getId()) {
                item.setQty(item.getQty() + qty);
                item.setSubTotal(new BigDecimal(book1.getOurPrice()).multiply(new BigDecimal(qty)));
                given(cartItemRepository.save(item)).willReturn(cartItem);

            }
            else {
                CartItem newCartItem = new CartItem();
                newCartItem.setShoppingCart(user.getShoppingCart());
                newCartItem.setBook(book1);

                newCartItem.setQty(qty);
                newCartItem.setSubTotal(new BigDecimal(book1.getOurPrice()).multiply(new BigDecimal(qty)));

                when(cartItemRepository.save(newCartItem)).thenReturn(newCartItem);

                BookToCartItem bookToCartItem1 = new BookToCartItem();
                bookToCartItem1.setBook(book1);
                bookToCartItem1.setCartItem(newCartItem);

                when(bookToCartItemRepository.save(bookToCartItem1)).thenReturn(bookToCartItem1);

              CartItem cartItem2=   cartItemService.addBookToCartItem(book1, user, qty);

                 Assert.assertNotNull(cartItem2);
                Mockito.verify(cartItemRepository).save(newCartItem);
                Mockito.verify(bookToCartItemRepository).save(bookToCartItem1);

            }

        }



    }


    @Test
    public void updateCartItemTest() throws Exception {
        Book book = new Book();
        book.setOurPrice(23);
        CartItem cartItem = new CartItem();
        cartItem.setQty(3);
        cartItem.setBook(book);

        BigDecimal bigDecimal = new BigDecimal(cartItem.getBook().getOurPrice()).multiply(new BigDecimal(cartItem.getQty()));

        bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        cartItem.setSubTotal(bigDecimal);

        when(cartItemRepository.save(cartItem)).thenReturn(cartItem);
        CartItem cartItem1 = cartItemService.updateCartItem(cartItem);

        Assert.assertNotNull(cartItem1);
        Assert.assertEquals(3, cartItem1.getQty());
        Mockito.verify(cartItemRepository).save(cartItem);
    }

    @Test
    public void findByIdTest() throws Exception {
        CartItem cartItem = new CartItem();
        cartItem.setId(1L);


        when(cartItemRepository.findOne(Mockito.anyLong())).thenReturn(cartItem);

        CartItem cartItem1 = cartItemService.findById(1L);

        Assert.assertEquals(cartItem, cartItem1);
        Mockito.verify(cartItemRepository).findOne(Mockito.anyLong());
    }

    @Test
    public void removeCartItemTest() throws Exception {
        CartItem cartItem = new CartItem();

        cartItemService.removeCartItem(cartItem);

        Mockito.verify(bookToCartItemRepository).deleteByCartItem(cartItem);
        Mockito.verify(cartItemRepository).delete(cartItem);
    }

    @Test
    public void saveCartItemTest() throws Exception {
        CartItem cartItem = new CartItem();
        cartItem.setQty(22);

        when(cartItemRepository.save(cartItem)).thenReturn(cartItem);

        CartItem cartItem1 = cartItemService.save(cartItem);
        Assert.assertEquals(22, cartItem1.getQty());

        Mockito.verify(cartItemRepository).save(cartItem);
    }

    @Test
    public void findByOrderTest() throws Exception {

        CartItem cartItem1 = new CartItem();
        CartItem cartItem2 = new CartItem();

        Order myOrder = new Order();

        when(cartItemRepository.findByOrder(myOrder)).thenReturn(Arrays.asList(cartItem1, cartItem2));

        List<CartItem> cartItemList = cartItemService.findByOrder(myOrder);

        Assert.assertNotNull(cartItemList);
        Assert.assertEquals(2, cartItemList.size());

        Mockito.verify(cartItemRepository).findByOrder(myOrder);
    }


}
