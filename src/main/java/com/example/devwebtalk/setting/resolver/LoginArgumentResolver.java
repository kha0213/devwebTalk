package com.example.devwebtalk.setting.resolver;

import com.example.devwebtalk.setting.annotation.Login;
import com.example.devwebtalk.setting.util.SessionUtil;
import com.example.devwebtalk.vo.LoginVO;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-08-09
 * Time: 오후 2:52
 */
@Component
public class LoginArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        final boolean hasAnnotation = methodParameter.hasParameterAnnotation(Login.class);
        final boolean typeCheck = LoginVO.class.isAssignableFrom(methodParameter.getParameterType());
        return hasAnnotation && typeCheck;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest webRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        final HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        return SessionUtil.getLoginVO(request);
    }
}
