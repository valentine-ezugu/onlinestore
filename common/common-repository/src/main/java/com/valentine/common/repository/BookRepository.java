package com.valentine.common.repository;

import com.valentine.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


/**
 * Created by Pc on 8/14/2017.
 */
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findAll();

    Book findOne(Long id);

    List<Book> findByCategory(String category);

    List<Book> findByTitleContaining(String title);

}
