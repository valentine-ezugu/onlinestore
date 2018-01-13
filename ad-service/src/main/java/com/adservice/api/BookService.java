package com.adservice.api;

import com.domain.domain.Book;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface BookService {

    Book save(Book book) throws DataAccessException;

    Book findOne(Long id) throws DataAccessException;

    List<Book> findAll() throws DataAccessException;

    void removeOne(Long id) throws DataAccessException;

}
