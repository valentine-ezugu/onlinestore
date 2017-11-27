package com.adminportal.service.api;

import com.adminportal.domain.Book;
import com.adminportal.dto.book.BookForSave;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface BookService {

    Book save(BookForSave bookForSave ) throws DataAccessException ;

    Book findOne(Long id)throws DataAccessException;

    List<Book> findAll()throws DataAccessException;

    void removeOne(Long id)throws DataAccessException;

}

