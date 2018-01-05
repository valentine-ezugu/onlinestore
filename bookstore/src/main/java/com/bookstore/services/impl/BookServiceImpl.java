package com.bookstore.services.impl;

import com.bookstore.domain.Book;
import com.bookstore.repository.BookRepository;
import com.bookstore.services.api.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> findAll()

    {
        return (List<Book>) bookRepository.findAll();
    }

    @Override
    public Book findOne(long id) {
        return bookRepository.findOne(id);
    }

    public List<Book> findByCategory(String category) {

        List<Book> bookList = bookRepository.findByCategory(category);

        List<Book> activeBookList = new ArrayList<>();

        for (Book book : bookList) {
            if (book.isActive()) {
                activeBookList.add(book);
            }
        }

        return activeBookList;
    }

    public List<Book> blurrySearch(String title) {
        List<Book> bookList = bookRepository.findByTitleContaining(title);
        List<Book> activeBookList = new ArrayList<>();

        for (Book book : bookList) {
            if (book.isActive()) {
                activeBookList.add(book);
            }
        }

        return activeBookList;
    }
}
