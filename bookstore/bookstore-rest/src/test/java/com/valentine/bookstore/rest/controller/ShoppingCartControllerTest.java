package com.valentine.bookstore.rest.controller;

import com.valentine.domain.Book;
import com.valentine.domain.CartItem;
import com.valentine.domain.User;
import com.valentine.common.service.BookService;
import com.valentine.bookstore.service.CartItemService;
import com.valentine.bookstore.service.SecurityService;
import com.valentine.bookstore.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(properties = "classpath:application.properties")
public class ShoppingCartControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HomeController homeController;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private ShoppingCartController shoppingCartController;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemService cartItemService;


    @Before
    public void setUp() {
        bookService = createMock(BookService.class);
        ReflectionTestUtils.setField(homeController, "bookService", bookService);
        ReflectionTestUtils.setField(shoppingCartController, "bookService", bookService);

        userService = createMock(UserService.class);
        ReflectionTestUtils.setField(shoppingCartController, "userService", userService);

        cartItemService = createMock(CartItemService.class);
        ReflectionTestUtils.setField(shoppingCartController, "cartItemService", cartItemService);

    }

    @After
    public void after() {
        SecurityContextHolder.clearContext();
    }

    @Test
    public void showLoginPage() throws Exception {

        mockMvc.perform(get("/login")
                .accept(MediaType.TEXT_HTML)
                .contentType(MediaType.TEXT_HTML)
        )
                .andExpect(model().attributeExists("classActiveLogin"))
                .andReturn();
    }


    @Test
    public void addItemToShoppingCart() throws Exception {

        securityService.autologin("V", "A");
        CartItem cartItem = new CartItem();

        String qty = "2";

        Book book = new Book();

        User user = new User();
        book.setId(1L);
        book.getId();

        cartItem.setBook(book);

        expect(userService.findByUsername(anyObject())).andReturn(user);
        expect(bookService.findOne(anyLong())).andReturn(book);

        expect(cartItemService.addBookToCartItem(book, user, Integer.parseInt(qty))).andReturn(cartItem);

        mockMvc
                .perform(get("/shoppingCart/addItem")
                        .accept(MediaType.TEXT_HTML)
                        .contentType(MediaType.TEXT_HTML)
                        .requestAttr("book", book)
                        .requestAttr("qty", qty))

                .andExpect(model().attributeExists("book"))
                .andExpect(model().attributeExists("qty"))

                .andExpect(view().name("forward:/bookDetail?id=" + cartItem.getId()))
                .andReturn();
    }


    @Test
    public void checkBookDetail() throws Exception {

        securityService.autologin("V", "A");

        Book book = new Book();
        book.setId(1L);
        expect(bookService.findOne(anyLong())).andReturn(book);
        replay(bookService);

        mockMvc
                .perform(get("/bookDetail")
                        .accept(MediaType.TEXT_HTML)
                        .contentType(MediaType.TEXT_HTML)
                        .param("id", "1"))
                .andExpect(model().attributeExists("book"))
                .andExpect(model().attributeExists("qty"))
                .andExpect(model().attributeExists("qtyList"))
                .andReturn();
    }

    @Test
    public void showBookShelf() throws Exception {
        List<Book> bookList = new ArrayList<>();

        securityService.autologin("V", "A");

        expect(bookService.findAll()).andReturn(bookList);
        replay(bookService);
        mockMvc.perform(get("/bookshelf")
                .accept(MediaType.TEXT_HTML)
                .contentType(MediaType.TEXT_HTML)
        )
                .andExpect(model().attributeExists("activeAll"))
                .andReturn();
    }


}
