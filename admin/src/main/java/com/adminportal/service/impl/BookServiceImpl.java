package com.adminportal.service.impl;

import com.adminportal.domain.Book;
import com.adminportal.dto.book.BookForSave;
import com.adminportal.repository.BookRepository;
import com.adminportal.service.api.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book save(BookForSave bookForSave) throws DataAccessException {

        Assert.hasText(bookForSave.getTitle(), "Title Required");
        Assert.hasText(bookForSave.getAuthor(), "author should have text");
        Assert.hasText(bookForSave.getCategory(), " category should have text");
        Assert.notNull(bookForSave.getIsbn(), "course start should be represented");
        Assert.notNull(bookForSave.getListPrice(), "List price cant be empty");
        Assert.notNull(bookForSave.getOurPrice(), "price cant be null");
        Assert.hasText(bookForSave.getFormat(), "enter Detail");
        Assert.notNull(bookForSave.getNumberOfPages(), "enter Detail");
        bookForSave.getPublisher();
        bookForSave.getShippingWeight();
        bookForSave.isActive();
        Assert.hasText(bookForSave.getPublicationDate(), "Date Required");
        Assert.hasText(bookForSave.getLanguage(), "Title Required");
        bookForSave.getDescription();
        Assert.notNull(bookForSave.getInStockNumber(), "stock number Required");
        Assert.notNull(bookForSave, "bookForSave should not be null");
        MultipartFile file = bookForSave.getBookImage();

        try {
            byte[] bytes = file.getBytes();
            String name = bookForSave.getId() + ".png";

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(new File("src/main/resources/static/image/book/" + name)));

            stream.write(bytes);
            stream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Book book = new Book(bookForSave);
        return bookRepository.save(book);
    }

    public List<Book> findAll() throws DataAccessException {
        return (List<Book>) bookRepository.findAll();
    }

    public Book findOne(Long id) throws DataAccessException {
        return bookRepository.findOne(id);
    }

    public void removeOne(Long id) throws DataAccessException {
        bookRepository.delete(id);
    }

}
