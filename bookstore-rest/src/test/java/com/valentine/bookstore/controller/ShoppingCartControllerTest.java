package com.valentine.bookstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valentine.bookstore.config.MappingConfig;
import com.valentine.domain.Book;
import com.valentine.domain.CartItem;
import com.valentine.domain.User;
import com.valentine.service.BookService;
import com.valentine.service.CartItemService;
import com.valentine.service.ShoppingCartService;
import com.valentine.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ShoppingCartController.class)
public class ShoppingCartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private UserService userService;

    @MockBean
    private CartItemService cartItemService;

    @MockBean
    private ShoppingCartService shoppingCartService;

    @Before
    public void setUp() {
    }

    @Test
    public void addItemToShoppingCart() throws Exception {

        CartItem cartItem = new CartItem();

        String qty = "2";

        Book book = new Book();

        User testUser = new User();
        book.setId(1L);
        book.getId();

        cartItem.setBook(book);

        when(userService.findByUsername(Matchers.anyString())).thenReturn(testUser);

        when(bookService.findOne(Matchers.anyLong())).thenReturn(book);


        when(cartItemService.addBookToCartItem(book, testUser, Integer.parseInt(qty))).thenReturn(cartItem);
        ObjectMapper mapper = new ObjectMapper();

        String bookAsString = mapper.writeValueAsString(book);
        mockMvc
                .perform(get("/shoppingCart/addItem")
                        .with(user("user").password("pass").roles("USER"))
                        .accept(MediaType.TEXT_HTML)
                        .contentType(MediaType.TEXT_HTML)
                        .param("book", bookAsString)
                        .param("qty", qty))
                .andExpect(status().isOk())
                .andReturn();
    }


    @Configuration
    @Import(MappingConfig.class)
    public static class TestConf {
        @Bean
        ShoppingCartController shoppingCartController() {
            return new ShoppingCartController();
        }
    }

}