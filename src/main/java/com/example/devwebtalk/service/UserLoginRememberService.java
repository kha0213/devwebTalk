package com.example.devwebtalk.service;

import com.example.devwebtalk.dto.UserLoginDto;
import com.example.devwebtalk.entity.User;
import com.example.devwebtalk.entity.UserLoginRemember;
import com.example.devwebtalk.exception.NonExistentUserException;
import com.example.devwebtalk.repository.UserLoginRememberRepository;
import com.example.devwebtalk.repository.UserRepository;
import com.example.devwebtalk.setting.util.SEEDUtil;
import com.example.devwebtalk.vo.LoginVO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
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
@PropertySource(value = "classpath:message/errors.properties")
@Getter
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
        UserLoginRemember loginRemember = getLoginRememberByDB(userLoginDto.getEmail());
        loginRemember.setLastModifiedDate(LocalDateTime.now());
        if (loginRemember.isNew()) {
            userLoginRememberRepository.save(loginRemember);
        }
        return loginRemember.getId();
    }

    private UserLoginRemember getLoginRememberByDB(String email) {
        final String cookieValue = SEEDUtil.encrypt(email);
        Optional<UserLoginRemember> userLoginRemember =
                userLoginRememberRepository.findUserLoginRememberByCookieValue(cookieValue);
        return userLoginRemember.orElseGet(
                () -> new UserLoginRemember(cookieValue,userRepository.findByEmail(email).get()));
    }

    public boolean isValidLoginCookie(Cookie loginCookie) {
        if (loginCookie == null) {
            return false;
        }
        final String encryptEmail = loginCookie.getValue();
        return userLoginRememberRepository.findByCookieValue(encryptEmail).isPresent();
    }

    public void remove(LoginVO loginVO) {
        String cookieValue = SEEDUtil.encrypt(loginVO.getEmail());
        userLoginRememberRepository.deleteByCookieValue(cookieValue);
    }
}
