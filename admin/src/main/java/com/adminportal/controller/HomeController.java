package com.adminportal.controller;

import com.adminportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Pc on 8/13/2017.
 */
@Controller
public class HomeController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String index() {
        return "redirect:home";
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    //for test purposes only
    @RequestMapping("/allUsers")
    public  String   allUsers(){
        userRepository.findAll();
        return "found all";
    }

}


