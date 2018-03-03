package com.valentine.adminportal.controller;

import com.valentine.domain.Book;
import com.valentine.dto.book.BookDetailForList;
import com.valentine.dto.book.BookDetailLite;
import com.valentine.dto.book.BookForSave;
import com.valentine.service.BookService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Controller
@RequestMapping("/book")
public class BookController {
    /**
     *
     */
    @Autowired
    private BookService bookService;
    /**
     *
     */
    @Autowired
    private Mapper mapper;

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Book getBookById(@PathVariable(value = "id") Long id) {
        return bookService.findOne(id);
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addBook(Model model) {
        Book book = new Book();

        BookForSave bookForSave = mapper.map(book, BookForSave.class, "bookForSave");

        model.addAttribute("book", new Book());
        return "addBook";
    }

    /**
     *
     * @param book
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addBookPost(@ModelAttribute("book") Book book, HttpServletRequest request, Model model) {
        bookService.save(book);

        MultipartFile bookImage = book.getBookImage();
        try {
            byte[] bytes = bookImage.getBytes();
            String name = book.getId() + ".png";
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(new File("src/main/resources/static/image/book/" + name)));
            stream.write(bytes);
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:bookList";
    }

    /**
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/bookInfo")
    public String bookInfo(@RequestParam("id") Long id, Model model) {

        Book book = bookService.findOne(id);

        BookDetailLite bookDetailLite = mapper.map(book, BookDetailLite.class, "bookDetailLiteId");

        model.addAttribute("book", bookDetailLite);
        return "bookInfo";
    }

    /**
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/updateBook")
    public String updateBook(@RequestParam("id") Long id, Model model) {
        Book book = bookService.findOne(id);

        BookForSave bookForSave = mapper.map(book, BookForSave.class, "bookForSave");

        model.addAttribute("book", bookForSave);
        return "updateBook";
    }

    /**
     *
     * @param book
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateBook", method = RequestMethod.POST)
    public String updateBookPost(@ModelAttribute("book") Book book, HttpServletRequest request) {

        bookService.save(book);
        return "redirect:/book/bookInfo?id=" + book.getId();
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping("/bookList")
    public String bookList(Model model) {

        List<Book> bookList = bookService.findAll();

        List<BookDetailForList> bookDetailForList = new ArrayList<>();
        for (Book book : bookList) {
            bookDetailForList.add(mapper.map(book, BookDetailForList.class, "bookListDto"));
        }
        model.addAttribute("bookList", bookDetailForList);
        return "bookList";
    }

    /**
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    public String remove(@ModelAttribute("id") String id, Model model) {

        bookService.removeOne(Long.parseLong(id.substring(8)));

        List<Book> bookList = bookService.findAll();

        List<BookDetailForList> bookListToFront = new ArrayList<>();
        for (Book book : bookList) {
            bookListToFront.add(mapper.map(book, BookDetailForList.class, "bookListDto"));
        }

        model.addAttribute("bookList", bookListToFront);
        return "redirect:/book/bookList";
    }

}