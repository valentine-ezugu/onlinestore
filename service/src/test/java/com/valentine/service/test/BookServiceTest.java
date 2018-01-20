package com.valentine.service.test;

import com.valentine.domain.Book;
import com.valentine.repository.BookRepository;
import com.valentine.service.BookService;

import com.valentine.utility.SecurityUtility;
import org.easymock.EasyMockSupport;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static org.easymock.EasyMock.*;

@Transactional
@ContextConfiguration
public class BookServiceTest extends AbstractTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    EasyMockSupport support = new EasyMockSupport();

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

        List<Book> bookList = bookService.findByCategory(anyString());

        support.verifyAll();
        Assert.assertNotNull(bookList);

        Assert.assertEquals(2, bookList.size());
        Assert.assertEquals(book2, bookList.get(1));
    }

    @Test
    public void blurrySearchTest() throws Exception {
        Book book = new Book();
        book.setActive(true);

        Book book1 = new Book();
        book1.setActive(false);

        expect(bookRepository.findByTitleContaining("management")).andReturn(Arrays.asList(book, book1));
        replay(bookRepository);

        List<Book> bookList = bookService.blurrySearch("management");

        support.verifyAll();
        Assert.assertNotNull(bookList);
        Assert.assertEquals(1, bookList.size());
    }
}