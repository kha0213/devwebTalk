package com.example.devwebtalk.converter;

import com.example.devwebtalk.entity.type.SocialType;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.util.Locale;

/**
 * String을 Enum으로 변환해주는 converter
 * 2021-07-26
 * Created By Kim Young Long
 * Blog : https://kha0213.github.io/
 * GitHub : https://github.com/kha0213
 */
@Configuration
public class StringToSocialType implements Converter<String, SocialType> {

    @Override
    public SocialType convert(String value) {
        return SocialType.valueOf(value.toUpperCase());
    }
}

