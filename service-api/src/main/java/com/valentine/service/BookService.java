package com.valentine.service;


import com.valentine.domain.Book;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Created by Pc on 8/15/2017.
 */
public interface BookService {
    List<Book> findAll() throws DataAccessException;

    List<Book> findByCategory(String category) throws DataAccessException;

    List<Book> blurrySearch(String title) throws DataAccessException;

    Book save(Book book) throws DataAccessException;

    Book findOne(Long id) throws DataAccessException;

    void removeOne(Long id) throws DataAccessException;

}
