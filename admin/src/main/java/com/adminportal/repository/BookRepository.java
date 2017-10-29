package com.adminportal.repository;

import com.adminportal.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


/**
 * Created by Pc on 8/14/2017.
 */
public interface BookRepository extends CrudRepository<Book, Long > {

    List<Book> findAll();
    Book findOne(Long id);


}
