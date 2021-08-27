package com.example.devwebtalk.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * LoginVO
 * 2021-07-26
 * Created By Kim Young Long
 * Blog : https://kha0213.github.io/
 * GitHub : https://github.com/kha0213
 */
@NoArgsConstructor
@Data
public class LoginVO {

    private String email;

    private LocalDateTime loginTime;

    public LoginVO(String email) {
        this.email = email;
        this.loginTime = LocalDateTime.now();
    }
}
