package com.adminportal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.adminportal.domain.Book;
import com.adminportal.repository.BookRepository;
import com.adminportal.service.api.BookService;

import javax.transaction.Transactional;


@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book save(Book book) throws DataAccessException
    {
        return bookRepository.save(book);
    }

    public List<Book> findAll()throws DataAccessException {
        return (List<Book>) bookRepository.findAll();
    }

    public Book findOne(Long id)throws DataAccessException {
        return bookRepository.findOne(id);
    }

    public void removeOne(Long id)throws DataAccessException {
        bookRepository.delete(id);
    }

}
