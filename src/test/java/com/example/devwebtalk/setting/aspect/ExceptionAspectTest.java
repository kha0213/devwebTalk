package com.example.devwebtalk.setting.aspect;

import com.example.devwebtalk.dto.UserLoginDto;
import com.example.devwebtalk.exception.NonExistentUserException;
import com.example.devwebtalk.service.UserLoginRememberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class ExceptionAspectTest {
    @Autowired
    UserLoginRememberService userLoginRememberService;

    @Test
    @DisplayName("userLoginRememberService 의 save 메소드에서 AfterThrow 되는지 테스트")
    void afterThrowTest() {
        //given
        UserLoginDto userLoginDto = new UserLoginDto();
        //when, then
        assertThrows(NonExistentUserException.class, () -> userLoginRememberService.save(userLoginDto));
    }
}