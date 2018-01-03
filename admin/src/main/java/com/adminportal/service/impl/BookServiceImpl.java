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

    public Book save(Book book)throws DataAccessException  {
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
