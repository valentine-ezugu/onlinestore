package com.valentine.service;

import com.valentine.domain.Book;
import com.valentine.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {


    private BookRepository bookRepository;

    @Autowired
    public void sBookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book save(Book book) throws DataAccessException {
        return bookRepository.save(book);
    }

    public List<Book> findAll() throws DataAccessException {
        return (List<Book>) bookRepository.findAll();
    }

    public Book findOne(Long id) throws DataAccessException {
        return bookRepository.findOne(id);
    }

    public void removeOne(Long id) throws DataAccessException {
        bookRepository.delete(id);
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