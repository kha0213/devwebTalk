package com.example.devwebtalk.dto;

import com.example.devwebtalk.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-08-21
 * Time: 오전 11:15
 */
@Data
@NoArgsConstructor
public class UserLoginDto {
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String pw;

    private boolean rememberLogin;

    public UserLoginDto(User user) {
        this.email = user.getEmail();
        this.pw = user.getPw();
    }

}
