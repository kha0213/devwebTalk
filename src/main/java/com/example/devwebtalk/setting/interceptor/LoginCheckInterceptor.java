package com.example.devwebtalk.setting.interceptor;

import com.example.devwebtalk.setting.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-08-08
 * Time: 오전 9:20
 */
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
   @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String requestURI = request.getRequestURI();
        log.info("Login Check Interceptor preHandle [URI] {}",requestURI);
        if (SessionUtil.isGuestUser(request)) {
            log.info("is Guest user [{}]", requestURI);
            // 로그인 전 사용자면 로그인으로 리다이렉트
            response.sendRedirect("/user/login?redirectURL=" + requestURI);
            return false;
        }
        return true;
    }
}
