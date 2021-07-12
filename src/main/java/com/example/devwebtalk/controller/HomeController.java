package com.example.devwebtalk.controller;

import com.example.devwebtalk.entity.User;
import com.example.devwebtalk.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-07-10
 * Time: 오후 3:29
 */
@Controller
public class HomeController {
    final Logger logger = LoggerFactory.getLogger(HomeController.class);

    final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/test")
    public String UserTest(Model model) {
        User user = new User("영롱");
        final Long userAdd = userService.userAdd(user);
        logger.info("userAdd !! user : " + user);
        model.addAttribute("user", user);
        return "testUser";
    }

    @GetMapping(value = "/login")
    public String Login() {
        return "/user/login";
    }
}
