package com.valentine.adminportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@Controller
public class HomeController {
    /**
     *
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "redirect:home";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

}
