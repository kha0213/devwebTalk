package com.example.devwebtalk.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.annotation.Nullable;
import java.time.LocalDateTime;

/**
 * Error시 리턴할 객체
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-08-10
 * Time: 오후 3:14
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ErrorResult {
    // 에러코드
    private final String code;
    // 에러클래스
    private final String errorClass;
    // 에러내용
    private final String description;
    // 발생시각
    @Nullable
    private LocalDateTime occurTime;
    // 요청 URL
    @Nullable
    private String requestURL;

}
