package com.adminportal.service.api;

import com.adminportal.domain.Book;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Created by Pc on 8/14/2017.
 */
public interface BookService {

    Book save(Book book)throws DataAccessException;

    Book findOne(Long id)throws DataAccessException;

    List<Book> findAll()throws DataAccessException;

    void removeOne(Long id)throws DataAccessException;

}

