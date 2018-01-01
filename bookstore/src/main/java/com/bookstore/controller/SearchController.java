package com.bookstore.controller;

import com.bookstore.domain.*;
import com.bookstore.dto.book.BookDetailForList;
import com.bookstore.dto.user.UserForProfile;
import com.bookstore.services.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
public class SearchController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @RequestMapping("/searchByCategory")
    public String searchByCategory(
            @RequestParam("category") String category,
            Model model, Principal principal
    ) {
        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            UserForProfile userForProfile = new UserForProfile(user);
            model.addAttribute("user", userForProfile);
        }

        String classActiveCategory = "active" + category;
        classActiveCategory = classActiveCategory.replaceAll("\\s+", "");
        classActiveCategory = classActiveCategory.replaceAll("&", "");
        model.addAttribute(classActiveCategory, true);

        List<Book> bookList = bookService.findByCategory(category);

        if (bookList.isEmpty()) {
            model.addAttribute("emptyList", true);
            return "bookshelf";
        }
        List<BookDetailForList> bookListToFront = new ArrayList<>();
        for(Book book : bookList){
            bookListToFront.add(new BookDetailForList(book));
        }
        model.addAttribute("bookList", bookListToFront);

        return "bookshelf";
    }

    @RequestMapping("/searchBook")
    public String searchBook(
            @ModelAttribute("keyword") String keyword,
            Principal principal, Model model
    ) {
        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }

        List<Book> bookList = bookService.blurrySearch(keyword);

        if (bookList.isEmpty()) {
            model.addAttribute("emptyList", true);
            return "bookshelf";
        }
        List<BookDetailForList> bookListToFront = new ArrayList<>();
        for(Book book : bookList){
            bookListToFront.add(new BookDetailForList(book));
        }
        model.addAttribute("bookList", bookListToFront);
        return "bookshelf";
    }
}
