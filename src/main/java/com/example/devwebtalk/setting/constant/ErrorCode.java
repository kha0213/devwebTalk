package com.example.devwebtalk.setting.constant;

import lombok.Getter;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-08-08
 * Time: 오후 9:16
 */
@Getter
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "C001", " Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "C002", " Invalid Input Value");

    private int status;
    private final String code;
    private final String description;

    ErrorCode(final int status, final String code, final String description) {
        this.status = status;
        this.description = description;
        this.code = code;
    }
}
