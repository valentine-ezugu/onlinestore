package com.valentine.adminportal.controller;

import com.valentine.adminportal.config.MappingConfig;
import com.valentine.domain.Book;
import com.valentine.service.BookService;
import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {


    Book book;

    @MockBean
    private BookService bookService;

    @MockBean
    private Mapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUpBook() throws Exception {
        book = new Book();
        book.setId(1L);
        book.setTitle("Managment");
    }

    @Test
    public void TestWithoutAuthentication() throws Exception {

        assertThat(this.bookService).isNotNull();

        mockMvc.perform(post("/book/remove").with(user("admin").password("admin").roles("WRONG")))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void addBookClicked() throws Exception {

        Book book1 = new Book();
        mockMvc.perform(get("/book/add").with(user("admin").password("admin").roles("USER", "ADMIN"))

                .accept(MediaType.TEXT_HTML)
                .contentType(MediaType.TEXT_HTML).sessionAttr("book", book1))
                .andExpect(status().is2xxSuccessful()).andDo(print())

                .andExpect(model().attributeExists("book"))

                .andExpect(view().name("addBook"))
                .andReturn();

    }

    @Test
    public void bookRemoveTest() throws Exception {

        assertThat(bookService).isNotNull();
        List<Book> expectedBookList = new ArrayList<>();

        bookService.removeOne(Matchers.anyLong());
        bookService.removeOne(Matchers.anyLong());

        when(bookService.findAll()).thenReturn(expectedBookList);
        mockMvc
                .perform(delete("/book/remove").with(user("admin").password("admin").roles("USER", "ADMIN"))
                        .accept(MediaType.TEXT_HTML)
                        .contentType(MediaType.TEXT_HTML)
                        .param("id", "012345678"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/book/bookList"))
                .andReturn();
    }

    @Test
    public void showBookListPage() throws Exception {

        List<Book> expectedBookList = new ArrayList<>();//createBookList(10);

        when(bookService.findAll()).thenReturn(expectedBookList);

        mockMvc
                .perform(get("/book/bookList").with(user("admin").password("pass").roles("USER", "ADMIN"))
                        .accept(MediaType.TEXT_HTML)
                        .contentType(MediaType.TEXT_HTML)).andExpect(status().isOk())
                .andExpect(model().attributeExists("bookList"))
                .andExpect(view().name("bookList"))
                .andReturn();

        Mockito.verify(bookService).findAll();
    }

    private List<Book> createBookList(int count) {
        List<Book> bookList = new ArrayList<Book>();
        for (int i = 0; i < count; i++) {
            bookList.add(new Book());
        }
        return bookList;
    }

    @Configuration
    @Import(MappingConfig.class)
    public static class TestConf {
        @Bean
        BookController bookController() {
            return new BookController();
        }
    }

}