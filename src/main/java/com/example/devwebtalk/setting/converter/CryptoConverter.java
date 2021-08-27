package com.example.devwebtalk.setting.converter;

import com.example.devwebtalk.setting.util.SEEDUtil;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-08-26
 * Time: 오후 2:40
 */
@Converter
public class CryptoConverter implements AttributeConverter<String, String> {
    @Override
    public String convertToDatabaseColumn(String attribute) {
        if (attribute == null) {
            return null;
        }
        return SEEDUtil.encrypt(attribute);
    }
    @Override
    public String convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return SEEDUtil.decrypt(dbData);
    }
}

