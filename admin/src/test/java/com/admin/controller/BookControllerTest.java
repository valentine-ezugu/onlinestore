package com.admin.controller;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.*;

import com.adminportal.AdminPortalApplication;
import com.adminportal.controller.BookController;
import com.adminportal.domain.Book;
import com.adminportal.service.api.BookService;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebAppConfiguration
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AdminPortalApplication.class)
public class BookControllerTest {

    private final String FILE_LOCATION = "1.png";

    @Autowired
    BookController bookController;

    @Autowired
    MockMvc mockMvc;

    BookService bookService;

    MockMvcBuilder mockMvcBuilder;

    @Before
    public void setUp() {
        bookService = createMock(BookService.class);
        ReflectionTestUtils.setField(bookController, "bookService", bookService);

    }

    @After
    public void tearDown() {
        reset(bookService);
    }

    @Test
    public void TesttWithoutAuthentication() throws Exception {
        mockMvc.perform(get("/book/remove"))
                .andExpect(status().is3xxRedirection());
    }



    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void addBookClicked() throws Exception {
        Book book = new Book();
        mockMvc
                .perform(get("/book/add")
                        .accept(MediaType.TEXT_HTML)
                        .contentType(MediaType.TEXT_HTML))

                .andExpect(model().attributeExists("book"))
                .andExpect(view().name("addBook"))
                .andReturn();
    }


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void bookRemoveTest() throws Exception {
        Book book = new Book();
        List<Book> expectedBookList = createBookList(10);


        bookService.removeOne(anyLong());
        EasyMock.expectLastCall();
        bookService.removeOne(anyLong());
        expect(bookService.findAll()).andReturn(expectedBookList);
        replay(bookService);


        mockMvc
                .perform(post("/book/remove")
                        .accept(MediaType.TEXT_HTML)
                        .contentType(MediaType.TEXT_HTML)
                        .param("id", "012345678"))

                .andExpect(view().name("redirect:/book/bookList"))
                .andReturn();
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void showBookListPage() throws Exception {

        Book book = new Book();
        List<Book> expectedBookList = createBookList(10);

        expect(bookService.findAll()).andReturn(expectedBookList);

        mockMvc
                .perform(get("/book/bookList")
                        .accept(MediaType.TEXT_HTML)
                        .contentType(MediaType.TEXT_HTML))

                .andExpect(view().name("bookList"))
                .andReturn();
    }


    private List<Book> createBookList(int count) {
        List<Book> bookList = new ArrayList<Book>();
        for (int i = 0; i < count; i++) {
            bookList.add(new Book());
        }
        return bookList;
    }
}
