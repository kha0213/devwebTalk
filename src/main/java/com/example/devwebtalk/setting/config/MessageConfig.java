package com.example.devwebtalk.setting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

/**
 *
 * 해당 Bean을 사용하게 되면 application.properties 설정이 먹지 않음.
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-08-23
 * Time: 오후 2:57
 */
//@Configuration
public class MessageConfig {
   /* @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source= new ResourceBundleMessageSource();
        source.setBasenames("message/errors");
        source.setUseCodeAsDefaultMessage(true);
        source.setDefaultEncoding("UTF-8");
        source.setDefaultLocale(Locale.KOREA);
        return source;
    }*/
}
