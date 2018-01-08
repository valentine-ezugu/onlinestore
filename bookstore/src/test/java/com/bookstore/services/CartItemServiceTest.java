package com.bookstore.services;

import com.bookstore.config.SecurityConfig;
import com.bookstore.domain.Book;
import com.bookstore.domain.CartItem;
import com.bookstore.repository.CartItemRepository;
import com.bookstore.services.api.CartItemService;
import com.bookstore.services.impl.UserSecurityService;
import com.bookstore.utility.SecurityUtility;
import com.bookstore.ws.AbstractTest;
import org.easymock.EasyMockSupport;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.easymock.EasyMock.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SecurityConfig.class, SecurityUtility.class, UserSecurityService.class, CartItemService.class
})
@Transactional
public class CartItemServiceTest extends AbstractTest {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartItemService cartItemService;

    private EasyMockSupport support = new EasyMockSupport();

    @Before
    public void setUp() {
        cartItemRepository = createMock(CartItemRepository.class);
        ReflectionTestUtils.setField(cartItemService, "cartItemRepository", cartItemRepository);
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

        expect(cartItemRepository.save(cartItem)).andReturn(cartItem);
        replay(cartItemRepository);

        CartItem cartItem1 = cartItemService.updateCartItem(cartItem);
        support.verifyAll();

        Assert.assertNotNull(cartItem1);
        Assert.assertEquals(3, cartItem1.getQty());
    }

}
