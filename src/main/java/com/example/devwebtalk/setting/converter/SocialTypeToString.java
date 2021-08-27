package com.example.devwebtalk.setting.converter;

import com.example.devwebtalk.entity.type.SocialType;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

/**
 * SocialType enum을 String으로 변환해주는 Converter
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-08-27
 * Time: 오전 12:43
 */
@Configuration
public class SocialTypeToString implements Converter<SocialType, String> {
    @Override
    public String convert(SocialType value) {
        return value.toString();
    }
}
