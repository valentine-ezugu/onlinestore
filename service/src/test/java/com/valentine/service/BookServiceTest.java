package com.valentine.service;

import com.valentine.domain.Book;
import com.valentine.repository.BookRepository;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookServiceImpl.class)
public class BookServiceTest {

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @Rule
    public final ExpectedException exception = ExpectedException.none();


    @Test
    public void findByCategoryTest() throws Exception {

        Book book1 = new Book();
        book1.setActive(true);

        Book book2 = new Book();
        book2.setActive(true);

        when(bookRepository.findByCategoryAndActiveIsTrue("category")).thenReturn(Arrays.asList(book1, book2));

        List<Book> bookList = bookService.findByCategory("category");
        Assert.assertNotNull(bookList);

        Assert.assertEquals(2, bookList.size());
        Assert.assertEquals(book2, bookList.get(1));

        Mockito.verify(bookRepository).findByCategoryAndActiveIsTrue("category");
    }

    @Test
    public void blurrySearchTest() throws Exception {
        Book book = new Book();
        book.setActive(true);

        Book book1 = new Book();
        book1.setActive(true);

        when(bookRepository.findByTitleContainingAndActiveIsTrue("management")).thenReturn(Arrays.asList(book, book1));

        List<Book> bookList = bookService.blurrySearch("management");

        Assert.assertNotNull(bookList);
        Assert.assertEquals(2, bookList.size());

        Mockito.verify(bookRepository).findByTitleContainingAndActiveIsTrue("management");
    }

    @Test
    public void removeOneBookTest() throws Exception {
        Book book = new Book();
        book.setId(1L);
        bookService.removeOne(book.getId());

        Mockito.verify(bookRepository).delete(book.getId());
    }

    @Test
    public void findOneTest() throws Exception {
        Book book = new Book();
        book.setId(2L);

        when(bookRepository.findOne(book.getId())).thenReturn(book);

        Book book1 = bookService.findOne(2L);

        Assert.assertEquals(book, book1);
        Mockito.verify(bookRepository).findOne(book.getId());
    }

    @Test
    public void findAllBooksTest() throws Exception {
        Book book = new Book();
        book.setId(1L);

        Book book1 = new Book();
        book1.setId(2L);

        Book book2 = new Book();
        book2.setId(3L);

        when(bookRepository.findAll()).thenReturn(Arrays.asList(book, book1, book2));

        List<Book> bookList = bookService.findAll();

        Assert.assertNotNull(bookList);
        Assert.assertEquals(3, bookList.size());

        Mockito.verify(bookRepository).findAll();
    }

    @Test
    public void saveBookTest() throws Exception {
        Book book = new Book();
        book.setId(10L);

        when(bookRepository.save(book)).thenReturn(book);

        Book book1 = bookService.save(book);

        Assert.assertEquals(book.getId(), book1.getId());
        Assert.assertEquals(book, book1);
        Mockito.verify(bookRepository).save(book);

    }


    @Test
    public void removeThrowsNullPointerException() {

       Mockito.doThrow(new DataAccessException(""){}).when(bookRepository).delete(1L);

        BookService foo = new BookServiceImpl(bookRepository);

        exception.expect(DataAccessException.class);
        foo.removeOne(1L);
    }


    @Test
    public void findOneThrowsDataAccessException() {

        when(bookRepository.findOne(1L)).thenThrow(new DataAccessException(""){} );

        BookService foo = new BookServiceImpl(bookRepository);
        exception.expect(DataAccessException.class);
        foo.findOne(1L);
   }

    @Test
      public void saveBookThrowsDataAccessException() {
       Book book = new Book();
       when(bookRepository.save(book)).thenThrow(new DataAccessException(""){} );

        BookService foo = new BookServiceImpl(bookRepository);

        exception.expect(DataAccessException.class);
        foo.save(book);
    }

}