package com.valentine.bookstore.controller;

import com.valentine.bookstore.config.MappingConfig;
import com.valentine.domain.Book;
import com.valentine.service.*;
import com.valentine.utility.MailConstructor;
import com.valentine.utility.SecurityUtility;
import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
public class HomeControllerTest {

    Book book;
    @MockBean
    private JavaMailSender mailSender;
    @MockBean
    private MailConstructor mailConstructor;
    @MockBean
    private BookService bookService;
    @MockBean
    private UserService userService;
    @MockBean
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @MockBean
    private OrderService orderService;
    @MockBean
    private CartItemService cartItemService;
    @MockBean
    private UserPaymentService userPaymentService;
    @MockBean
    private UserShippingService userShippingService;
    @MockBean
    private SecurityUtility securityUtility;
    @MockBean
    private Mapper mapper;
    @MockBean
    private RoleService roleService;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        book = new Book();
        book.setId(1L);
        book.setTitle("xx");
        book.setAuthor("dada");
        book.setOurPrice(22);
        book.setCategory("management");
        book.setLanguage("English");

    }

    @Test
    public void showLoginPage() throws Exception {

        mockMvc.perform(get("/login").with(user("user").password("pass").roles("USER"))
                .accept(MediaType.TEXT_HTML)
                .contentType(MediaType.TEXT_HTML)
        )
                .andExpect(model().attributeExists("classActiveLogin"))
                .andReturn();
    }

    @Test
    public void checkBookDetail() throws Exception {

        when(bookService.findOne(anyLong())).thenReturn(book);
        mockMvc
                .perform(get("/bookDetail").with(user("user").password("pass").roles("USER"))
                        .accept(MediaType.TEXT_HTML)
                        .contentType(MediaType.TEXT_HTML)
                        .param("id", "1"))

                .andExpect(status().isOk())

                .andExpect(model().attributeExists("book"))

                .andExpect(model().attributeExists("qty"))
                .andExpect(model().attributeExists("qtyList"))
                .andExpect(view().name("bookDetail"))
                .andReturn();
    }

    @Test
    public void showBookShelf() throws Exception {
        List<Book> bookList = new ArrayList<>();

        when(bookService.findAll()).thenReturn(bookList);

        mockMvc.perform(get("/bookshelf").with(user("user").password("pass").roles("USER"))
                .accept(MediaType.TEXT_HTML)
                .contentType(MediaType.TEXT_HTML)
        ).andExpect(status().isOk())
                .andExpect(model().attributeExists("activeAll"))
                .andReturn();
    }

    @Configuration
    @Import(MappingConfig.class)
    public static class TestConf {
        @Bean
        HomeController homeController() {
            return new HomeController();
        }

    }

}
