package com.valentine.service;


import com.valentine.domain.*;
import com.valentine.repository.CartItemRepository;
import com.valentine.repository.OrderRepository;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderServiceImpl.class)
public class OrderServiceTest {


    @MockBean
    private CartItemRepository cartItemRepository;

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;


    @Test
    public void createOrderTest() throws Exception{

        ShoppingCart shoppingCart = new ShoppingCart();
        ShippingAddress shippingAddress  = new ShippingAddress();
        BillingAddress billingAddress = new BillingAddress();
        Payment payment  = new Payment();
        String shippingMethod = "air";
        User user = new User();


        Order order = new Order();
        order.setBillingAddress(billingAddress);
        order.setOrderStatus("created");
        order.setPayment(payment);
        order.setShippingAddress(shippingAddress);
        order.setShippingMethod(shippingMethod);


        Book book1 = new Book();

        CartItem cartItem = new CartItem();
        cartItem.setBook(book1);

        Book book2 = new Book();
        CartItem cartItem1 = new CartItem();
        cartItem1.setBook(book2);

        when(cartItemRepository.findByShoppingCart(shoppingCart)).thenReturn(Arrays.asList(cartItem, cartItem1));

        for (CartItem cartItem2 : Arrays.asList(cartItem,cartItem1)) {
            Book book = cartItem2.getBook();
            cartItem2.setOrder(order);
            book.setInStockNumber(book.getInStockNumber() - cartItem2.getQty());
        }

        order.setCartItemList(Arrays.asList(cartItem,cartItem1));
        order.setOrderDate(Calendar.getInstance().getTime());
        order.setOrderTotal(shoppingCart.getGrandTotal());
        shippingAddress.setOrder(order);
        billingAddress.setOrder(order);
        payment.setOrder(order);
        order.setUser(user);

        when(orderRepository.save(order)).thenReturn(order);
        order = orderService.createOrder(shoppingCart, shippingAddress, billingAddress, payment, shippingMethod, user);

        Mockito.verify(cartItemRepository).findByShoppingCart(shoppingCart);
        Mockito.verify(orderRepository).save(order);
    }

    @Test
    public void findOneTest()throws Exception{
        Order order = new Order();
        order.setId(2l);
        when(orderRepository.findOne(2L)).thenReturn(order);

          Order order1=  orderService.findOne(2L);

          Assert.assertNotNull(order);
        Assert.assertEquals(order, order1);

        Mockito.verify(orderRepository).findOne(2L);
    }
}