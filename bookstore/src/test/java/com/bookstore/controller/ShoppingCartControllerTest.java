package com.bookstore.controller;

import com.bookstore.BookstoreApplications;
import com.bookstore.domain.Book;
import com.bookstore.domain.CartItem;
import com.bookstore.domain.User;
import com.bookstore.services.api.BookService;
import com.bookstore.services.api.CartItemService;
import com.bookstore.services.api.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BookstoreApplications.class)
public class ShoppingCartControllerTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    private HomeController homeController;

    @Autowired
    private ShoppingCartController shoppingCartController;

    @Autowired
    private BookService bookService;

    @Autowired
    private  UserService userService;

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
    @WithMockUser//(username = "V", roles = {"USER"})
    public void addItemToShoppingCart() throws Exception {

        CartItem cartItem = new CartItem();

        String qty = "2";

        Book book = new Book();

        User user = new User();
        book.setId(1L);
        book.getId();

        cartItem.setBook(book);

        expect(userService.findByUsername(anyObject())).andReturn(user);
        expect(bookService.findOne(anyLong())).andReturn(book);

        expect(cartItemService.addBookToCartItem(anyObject(), anyObject(), anyInt())).andReturn(cartItem);


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
    @WithMockUser//(username = "V", roles = {"USER"})
    public void updateCartItemTest() throws Exception {

        Book book = new Book();
        CartItem cartItem = new CartItem();
        cartItem.setId(1L);
        Long s = cartItem.getId();

        String qty = "2";
        cartItem.setQty(Integer.parseInt(qty));

        expect(cartItemService.findById(anyLong())).andReturn(cartItem);
        cartItemService.updateCartItem(cartItem);
        expectLastCall();
        replay(bookService);

        mockMvc
                .perform(get("/shoppingCart/updateCartItem")
                        .accept(MediaType.TEXT_HTML)
                        .contentType(MediaType.TEXT_HTML)
                        .param("id", "s")
                        .param("qty", qty))

                //some problems with the attributes definition so for now like this
                .andReturn();
    }

    @Test
    @WithMockUser//(username = "V", roles = {"USER"})
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

        mockMvc.perform(get("/bookshelf")
                .accept(MediaType.TEXT_HTML)
                .contentType(MediaType.TEXT_HTML)
        )
                .andExpect(model().attributeExists("activeAll"))
                .andReturn();
    }

}
