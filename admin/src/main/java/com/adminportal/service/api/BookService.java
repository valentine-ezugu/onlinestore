package com.adminportal.service.api;

import com.adminportal.domain.Book;

import java.util.List;

/**
 * Created by Pc on 8/14/2017.
 */
public interface BookService {

    Book save(Book book);
    Book findOne(Long id);
    List<Book> findAll();

     void removeOne(Long id);

}

