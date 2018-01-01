package com.adminportal.service.impl;

import com.adminportal.domain.Book;
import com.adminportal.dto.book.BookForSave;
import com.adminportal.repository.BookRepository;
import com.adminportal.service.api.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    public void service(){
        logger.info("Message at INFO level from TestService.service()");
        logger.warn("Message at WARN level from TestService.service()");
    }


    @Autowired
    private BookRepository bookRepository;

    public Book save(Book book) {
         return bookRepository.save(book);
    }
//    public Book save(BookForSave bookForSave) throws DataAccessException {
//
//        Assert.hasText(bookForSave.getTitle(), "Title Required");
//        Assert.hasText(bookForSave.getAuthor(), "author should have text");
//        Assert.hasText(bookForSave.getCategory(), " category should have text");
//        Assert.notNull(bookForSave.getIsbn(), "Isbn should be represented");
//        Assert.notNull(bookForSave.getListPrice(), "List price cant be empty");
//        Assert.notNull(bookForSave.getOurPrice(), "price cant be null");
//        Assert.hasText(bookForSave.getFormat(), "enter Format  Detail");
//        Assert.notNull(bookForSave.getNumberOfPages(), "enter NumberOfPages Detail");
//        bookForSave.getPublisher();
//        bookForSave.getShippingWeight();
//        bookForSave.isActive();
//        Assert.hasText(bookForSave.getPublicationDate(), "PublicationDate Required");
//        Assert.hasText(bookForSave.getLanguage(), "Language Required");
//        bookForSave.getDescription();
//        Assert.notNull(bookForSave.getInStockNumber(), "stock number Required");
//        Assert.notNull(bookForSave, "bookForSave should not be null");
//        MultipartFile file = bookForSave.getBookImage();
//
//        try {
//            byte[] bytes = file.getBytes();
//            String name = bookForSave.getId() + ".png";
//
//            BufferedOutputStream stream = new BufferedOutputStream(
//                    new FileOutputStream(new File("src/main/resources/static/image/book/" + name)));
//
//            stream.write(bytes);
//            stream.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Book book = new Book(bookForSave);
//        return bookRepository.save(book);
//    }

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
