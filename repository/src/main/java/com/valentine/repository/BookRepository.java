package com.valentine.repository;

import com.valentine.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findByCategoryAndActiveIsTrue(String category);

    List<Book> findByTitleContainingAndActiveIsTrue(String title);
}
