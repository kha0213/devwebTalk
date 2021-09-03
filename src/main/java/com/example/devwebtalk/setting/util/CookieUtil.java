package com.example.devwebtalk.setting.util;

import com.example.devwebtalk.dto.UserLoginDto;
import com.example.devwebtalk.setting.constant.Cons;
import org.h2.security.AES;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-08-23
 * Time: 오전 11:31
 */
@Component
public class CookieUtil {

    public static Optional<Cookie> getCookieValue(HttpServletRequest req, String cookieName) {
        return Arrays.stream(req.getCookies())
                .filter(c -> c.getName().equals(cookieName))
                .findAny();
    }

    public static void saveCookie(HttpServletResponse res, Cookie cookie) {
        cookie.setMaxAge(Cons.COOKIE_DEFAULT_MAX_AGE);
        res.addCookie(cookie);
    }

    public static void deleteCookie(HttpServletResponse res, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        res.addCookie(cookie);
    }
}
