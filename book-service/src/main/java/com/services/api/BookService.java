package com.services.api;


import com.domain.domain.Book;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface BookService {
    List<Book> findAll() throws DataAccessException;

    Book findOne(long id) throws DataAccessException;

    List<Book> findByCategory(String category) throws DataAccessException;

    List<Book> blurrySearch(String title) throws DataAccessException;
}
