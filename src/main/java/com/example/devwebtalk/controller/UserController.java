package com.example.devwebtalk.controller;

import com.example.devwebtalk.dto.UserCreateDto;
import com.example.devwebtalk.entity.User;
import com.example.devwebtalk.entity.type.SocialType;
import com.example.devwebtalk.service.UserService;
import com.example.devwebtalk.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

import java.util.Optional;

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

    @GetMapping(value = "/user/socialJoin/{socialType}")
    public String socialJoinView(Model model, HttpServletRequest req, @PathVariable SocialType socialType) {
        if (SessionUtil.isGuestUser(req)) { // 로그인 안 한 유저면
            return "/user/socialJoinView";
        }
        return null;
    }

    @RequestMapping(value = "/user/login")
    public String loginView() {
        return "/user/login";
    }

    @GetMapping(value = "/user/join")
    public String userJoinView(Model model) {
        model.addAttribute("user", new UserCreateDto());
        return "/user/join";
    }

    @PostMapping(value = "/user/join")
    public String userJoin(@Validated @ModelAttribute("user") UserCreateDto user
            , BindingResult bindingResult
            , RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.info("[user join error] {}", bindingResult);
            return "/user/join";
        }

        Long userId = userService.join(user.convertUser());
        redirectAttributes.addAttribute("userId", userId);
        // TODO 회원 가입 후 메인으로 가는게 좋을 듯
        return "redirect:/user/info";
    }

    @GetMapping(value = "/user/info")
    public String userInfoView(@RequestParam("userId") Long userId
                                ,Model model) {
        Optional<User> user = userService.findById(userId);
        model.addAttribute("user", user.orElseGet(User::new));
        return "/user/info";
    }
}
