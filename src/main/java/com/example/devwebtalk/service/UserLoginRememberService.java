package com.example.devwebtalk.service;

import com.example.devwebtalk.dto.UserLoginDto;
import com.example.devwebtalk.entity.User;
import com.example.devwebtalk.entity.UserLoginRemember;
import com.example.devwebtalk.repository.UserLoginRememberRepository;
import com.example.devwebtalk.repository.UserRepository;
import com.example.devwebtalk.setting.util.CookieUtil;
import com.example.devwebtalk.setting.util.SEEDUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.transaction.Transactional;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-08-27
 * Time: 오후 8:19
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserLoginRememberService {

    private final UserLoginRememberRepository userLoginRememberRepository;
    private final UserRepository userRepository;

    /**
     * 쿠키 값으로 UserLoginDto 리턴하기
     * @param cookieValue
     * @return
     */
    public UserLoginDto findLoginDtoByCookieValue(String cookieValue) {
        final Optional<User> user = userLoginRememberRepository.findByCookieValue(cookieValue);
        if(user.isEmpty()) return null;
        return new UserLoginDto(user.get());
    }

    public Long save(UserLoginDto userLoginDto) {
        final String email = userLoginDto.getEmail();
        final Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            log.error("UserLoginRememberService.save ERROR user Not Found [email] {}", email);
            throw new NoSuchElementException("사용자를 찾을 수 없습니다.");
        }

        final String encryptEmail = SEEDUtil.encrypt(email);
        final UserLoginRemember loginRemember = new UserLoginRemember(encryptEmail, user.get());
        userLoginRememberRepository.save(loginRemember);

        return loginRemember.getId();
    }

    public boolean isValidLoginCookie(Cookie loginCookie) {
        if (loginCookie == null) {
            return false;
        }
        final String encryptEmail = loginCookie.getValue();
        return userLoginRememberRepository.findByCookieValue(encryptEmail).isPresent();
    }
}
