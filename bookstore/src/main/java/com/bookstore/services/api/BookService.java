package com.bookstore.services.api;


import com.bookstore.domain.*;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Created by Pc on 8/15/2017.
 */
public interface BookService {
    List<Book> findAll() throws DataAccessException;
    Book findOne(long id)throws DataAccessException;

    List<Book> findByCategory(String category)throws DataAccessException;

    List<Book> blurrySearch(String title)throws DataAccessException;
}
