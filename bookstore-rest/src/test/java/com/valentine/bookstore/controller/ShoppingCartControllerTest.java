package com.valentine.bookstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valentine.bookstore.BookstoreApplications;
import com.valentine.domain.Book;
import com.valentine.domain.CartItem;
import com.valentine.domain.User;
import com.valentine.service.BookService;
import com.valentine.service.CartItemService;
import com.valentine.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BookstoreApplications.class, properties = "classpath:application.properties")
public class ShoppingCartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HomeController homeController;

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
    public void tearDown() {
        reset(bookService);
        reset(userService);
        reset(cartItemService);

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
    @WithMockUser(username = "V", authorities = {"USER"})
    public void addItemToShoppingCart() throws Exception {

        CartItem cartItem = new CartItem();

        String qty = "2";

        Book book = new Book();

        User user = new User();
        book.setId(1L);
        book.getId();

        cartItem.setBook(book);

        expect(userService.findByUsername(anyString())).andReturn(user);
        replay(userService);

        expect(bookService.findOne(anyLong())).andReturn(book);
        replay(bookService);

        expect(cartItemService.addBookToCartItem(book, user, Integer.parseInt(qty))).andReturn(cartItem);
        ObjectMapper mapper = new ObjectMapper();

        String bookAsString = mapper.writeValueAsString(book);
        mockMvc
                .perform(get("/shoppingCart/addItem")
                        .accept(MediaType.TEXT_HTML)
                        .contentType(MediaType.TEXT_HTML)
                        .param("book", bookAsString)
                        .param("qty", qty))

                .andReturn();
    }


    @Test
    public void checkBookDetail() throws Exception {

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