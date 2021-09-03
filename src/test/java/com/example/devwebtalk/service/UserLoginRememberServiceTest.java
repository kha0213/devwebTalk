package com.example.devwebtalk.service;

import com.example.devwebtalk.dto.UserLoginDto;
import com.example.devwebtalk.entity.User;
import com.example.devwebtalk.repository.UserLoginRememberRepository;
import com.example.devwebtalk.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@Transactional
class UserLoginRememberServiceTest {
    @InjectMocks
    UserLoginRememberService userLoginRememberService;
    @InjectMocks
    UserService userService;

    @Mock
    UserLoginRememberRepository userLoginRememberRepository;
    @Mock
    UserRepository userRepository;

    @BeforeEach
    void before() {
    }

    @Test
    @DisplayName("UserLoginRemember 저장 테스트")
    void userLoginRemember_Test() {
        //given
        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setEmail("a@devweb.com");
        userLoginDto.setPw("123qwe!");
        userLoginDto.setRememberLogin(true);


        userLoginRememberService.save(userLoginDto);
    }

}