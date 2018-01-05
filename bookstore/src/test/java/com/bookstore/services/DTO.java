package com.bookstore.services;


import com.bookstore.domain.ShoppingCart;
import com.bookstore.domain.UserShipping;
import com.bookstore.dto.shoppingCart.ShoppingCartLite;
import com.bookstore.dto.user.UserForShippingLite;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.RoleRepository;
import com.bookstore.repository.UserRepository;
import com.bookstore.services.api.UserService;

import com.bookstore.ws.AbstractTest;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.easymock.EasyMock.createMock;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration//(classes = {SecurityConfig.class})
public class DTO extends AbstractTest{

    private UserService userService;


    @Autowired
    private Mapper mapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BookRepository bookRepository;

    @Before
    public void setUp() {
        bookRepository = createMock(BookRepository.class);
        mapper = createMock(Mapper.class);
    }

    @Test
    public void dozerTest() throws Exception{
        List<String> list = new ArrayList<>();
        list.add("mapping.xml");
        mapper = new DozerBeanMapper(list);
        UserShipping userShipping = new UserShipping();
        userShipping.setUserShippingName("Chiko");
        UserForShippingLite userForShippingLite =  mapper.map(userShipping, UserForShippingLite.class, "userShippingLiteId");
        Assert.assertEquals(userShipping.getUserShippingName(), userForShippingLite.getUserShippingName());
    }

    @Test
    public void dtoTest(){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);
        ShoppingCartLite shoppingCartLite = new ShoppingCartLite(shoppingCart);
         assertThat(shoppingCartLite.getId()).isEqualTo(1L);
    }
}
