package com.bookstore.services;

import com.bookstore.config.SecurityConfig;
import com.rest_end.utility.SecurityUtility;
import com.bookstore.ws.AbstractTest;
import com.data.persistence.BookRepository;
import com.domain.domain.Book;
import com.services.api.BookService;
import com.services.impl.UserSecurityService;
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
import java.util.Arrays;
import java.util.List;

import static org.easymock.EasyMock.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SecurityConfig.class, SecurityUtility.class, UserSecurityService.class, BookService.class
})
@Transactional
public class BookServiceTest extends AbstractTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    private EasyMockSupport support = new EasyMockSupport();

    @Before
    public void setUp() {
        bookRepository = createMock(BookRepository.class);
        ReflectionTestUtils.setField(bookService, "bookRepository", bookRepository);
    }

    @Test
    public void findByCategoryTest() throws Exception {

        Book book1 = new Book();
        book1.setActive(true);

        Book book2 = new Book();
        book2.setActive(true);

        expect(bookRepository.findByCategory(anyString())).andReturn(Arrays.asList(book1, book2));
        replay(bookRepository);

        List<Book> bookList = bookService.findByCategory("category1");

        support.verifyAll();
        Assert.assertNotNull(bookList);

        Assert.assertEquals(2, bookList.size());
        Assert.assertEquals(book2, bookList.get(1));
    }

    @Test
    public void blurrySearchTest() throws Exception {
        Book book = new Book();
        book.isActive();

        Book book1 = new Book();
        book1.setActive(false);

        expect(bookRepository.findByTitleContaining(anyString())).andReturn(Arrays.asList(book, book1));
        replay(bookRepository);

        List<Book> bookList = bookService.blurrySearch("management");

        support.verifyAll();
        Assert.assertNotNull(bookList);
        Assert.assertEquals(1, bookList.size());

    }
}