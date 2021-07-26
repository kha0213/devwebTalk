package com.example.devwebtalk.controller;

import com.example.devwebtalk.entity.type.SocialType;
import com.example.devwebtalk.service.UserService;
import com.example.devwebtalk.util.SessionUtil;
import com.example.devwebtalk.vo.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * 2021-07-26
 * Created By Kim Young Long
 * Blog : https://kha0213.github.io/
 * GitHub : https://github.com/kha0213
 */
@Slf4j
@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/user/socialJoin/{socialType}", method = GET)
    public String socialJoinView(Model model, HttpServletRequest req, @PathVariable SocialType socialType) {
        if (SessionUtil.isGuestUser(req)) { // 로그인 안 한 유저면
            return "/user/socialJoinView";
        }
        return null;

    }
}
