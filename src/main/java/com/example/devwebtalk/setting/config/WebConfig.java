package com.example.devwebtalk.setting.config;

import com.example.devwebtalk.setting.interceptor.LoginCheckInterceptor;
import com.example.devwebtalk.setting.resolver.LoginArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-08-08
 * Time: 오전 9:25
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private static final String ALL_REQUEST = "/**";
    private static final List<String> DEFAULT_EXCLUDE_PATTERN
            = Arrays.asList("/error/**","/bootstrap/**","/**/*.ico","/**/*.js","/**/*.css","/css/**","/image/**","/js/**","/**/*ajax");
    private static final List<String> LOGIN_EXCLUDE_REQUEST
            = Arrays.asList("/","/index.html","/user/join","/user/login","/**/main","/user/logout");
    private final LoginArgumentResolver loginArgumentResolver;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns(ALL_REQUEST)
                .excludePathPatterns(DEFAULT_EXCLUDE_PATTERN)
                .excludePathPatterns(LOGIN_EXCLUDE_REQUEST);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginArgumentResolver);
    }
}
