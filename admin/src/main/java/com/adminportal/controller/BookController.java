package com.adminportal.controller;

import com.adminportal.domain.Book;
import com.adminportal.dto.book.BookDetailForList;
import com.adminportal.dto.book.BookDetailLite;
import com.adminportal.dto.book.BookForSave;
import com.adminportal.repository.BookRepository;
import com.adminportal.service.api.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Book getBookById(@PathVariable(value = "id") Long id) {
        return bookRepository.findOne(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addBook(Model model) {
        BookForSave bookForSave = new BookForSave();
        model.addAttribute("book", bookForSave);
        return "addBook";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addBookPost(@ModelAttribute("bookForSave") BookForSave bookForSave, HttpServletRequest request, Model model) {
        bookService.save(bookForSave);
        return "redirect:bookList";
    }

    @RequestMapping("/bookInfo")
    public String bookInfo(@RequestParam("id") Long id, Model model) {
        BookDetailLite book = new BookDetailLite(bookService.findOne(id));
        model.addAttribute("book", book);
        return "bookInfo";
    }

    @RequestMapping("/updateBook")
    public String updateBook(@RequestParam("id") Long id, Model model) {
        Book book = bookService.findOne(id);
        model.addAttribute("book", book);
        return "updateBook";
    }

    @RequestMapping(value = "/updateBook", method = RequestMethod.POST)
    public String updateBookPost(@ModelAttribute("book") BookForSave bookForSave, HttpServletRequest request) {
        bookService.save(bookForSave);
        return "redirect:/book/bookInfo?id=" + bookForSave.getId();
    }

    @RequestMapping("/bookList")
    public String bookList(Model model) {
        List<Book> bookList = bookService.findAll();
        List<BookDetailForList> bookListToFront = new ArrayList<>();
        for(Book book : bookList){
            bookListToFront.add(new BookDetailForList(book));
        }
        model.addAttribute("bookList", bookListToFront);
        return "bookList";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@ModelAttribute("id") String id, Model model) {
        bookService.removeOne(Long.parseLong(id.substring(8)));
        List<Book> bookList = bookService.findAll();
        List<BookDetailForList> bookListToFront = new ArrayList<>();
        for(Book book : bookList){
            bookListToFront.add(new BookDetailForList(book));
        }
        model.addAttribute("bookList", bookListToFront);
        return "redirect:/book/bookList";
    }
}
