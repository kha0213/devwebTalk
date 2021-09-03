package com.example.devwebtalk.controller;

import com.example.devwebtalk.dto.UserCreateDto;
import com.example.devwebtalk.dto.UserLoginDto;
import com.example.devwebtalk.dto.UserModifyDto;
import com.example.devwebtalk.entity.type.SocialType;
import com.example.devwebtalk.service.UserLoginRememberService;
import com.example.devwebtalk.service.UserService;
import com.example.devwebtalk.setting.annotation.Login;
import com.example.devwebtalk.setting.util.CookieUtil;
import com.example.devwebtalk.setting.util.SEEDUtil;
import com.example.devwebtalk.setting.util.SessionUtil;
import com.example.devwebtalk.vo.LoginVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Optional;

import static com.example.devwebtalk.setting.constant.Cons.*;

/**
 * 2021-07-26
 * Created By Kim Young Long
 * Blog : https://kha0213.github.io/
 * GitHub : https://github.com/kha0213
 */
@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class UserController {
    private String redirectMain = "redirect:friendList";
    private final UserService userService;
    private final UserLoginRememberService userLoginRememberService;

    @GetMapping(value = "/socialJoin/{socialType}")
    public String socialJoinView(Model model, HttpServletRequest req, @PathVariable SocialType socialType) {
        if (SessionUtil.isGuestUser(req)) { // 로그인 안 한 유저면
            model.addAttribute("socialType", socialType);
            return "social/socialJoinView";
        }
        return null;
    }

    @GetMapping(value = "/login")
    public String loginView(Model model
            , HttpServletRequest req
            , @RequestParam(defaultValue = "redirect:friendList") String redirectURL
            , @CookieValue(value= LOGIN_COOKIE, required=false) Cookie loginCookie) {
        if (userLoginRememberService.isValidLoginCookie(loginCookie)) {
                UserLoginDto userLoginDto = userLoginRememberService.findLoginDtoByCookieValue(loginCookie.getValue());
            if (userLoginDto != null) {
                setLoginVOInSession(req, userLoginDto);
                return redirectURL;
            }
        }
        model.addAttribute("user", new UserLoginDto());
        return "user/login";
    }

    private void setLoginVOInSession(HttpServletRequest req, UserLoginDto userLoginDto) {
        SessionUtil.setSessionValue(req, LOGIN_VO, new LoginVO(userLoginDto.getEmail()));
    }

    @PostMapping(value = "/login")
    public String login(@Validated @ModelAttribute("user") UserLoginDto userLoginDto
            , BindingResult bindingResult
            , @RequestParam(defaultValue = "redirect:friendList") String redirectURL
            , HttpServletRequest req
            , HttpServletResponse res) {

        userService.loginUser(userLoginDto, bindingResult);

        if (bindingResult.hasErrors()) {
            log.info("[user login fail] {}", bindingResult);
            return "user/login";
        }

        setLoginVOInSession(req, userLoginDto);
        if (userLoginDto.isRememberLogin()) {
            saveRememberLogin(res, userLoginDto);
        }
        return redirectURL;
    }


    private void saveRememberLogin(HttpServletResponse res, UserLoginDto userLoginDto) {
        userLoginRememberService.save(userLoginDto);
        final String encryptValue = SEEDUtil.encrypt(userLoginDto.getEmail());
        CookieUtil.saveCookie(res,new Cookie(LOGIN_COOKIE,encryptValue));
    }

    @GetMapping(value = "/join")
    public String userJoinView(Model model) {
        model.addAttribute("user", new UserCreateDto());
        return "user/join";
    }

    @PostMapping(value = "/join")
    public String userJoin(@Validated @ModelAttribute("user") UserCreateDto user
            , BindingResult bindingResult
            , RedirectAttributes redirectAttributes
            , HttpSession session) {

        if (bindingResult.hasErrors()) {
            log.info("[user join error] {}", bindingResult);
            return "user/join";
        }

        Long userId = userService.join(user.convertUser());
        session.setAttribute(LOGIN_VO,userService.findById(userId));
        redirectAttributes.addAttribute("userId", userId);
        return "redirect:login";
    }

    @GetMapping(value = "/info")
    public String userInfoView(@Login LoginVO loginVO, Model model) {
        UserModifyDto user = userService.findUserModifyDtoByEmail(loginVO.getEmail());
        model.addAttribute("user", user);
        return "user/info";
    }

    @GetMapping(value = "/logout")
    public String logout(@Login LoginVO loginVO, HttpSession session, HttpServletResponse res) {
        userLoginRememberService.remove(loginVO);
        CookieUtil.deleteCookie(res, LOGIN_COOKIE);
        session.removeAttribute(LOGIN_VO);
        return "redirect:login";
    }
}
