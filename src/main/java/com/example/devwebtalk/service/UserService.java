package com.example.devwebtalk.service;

import com.example.devwebtalk.dto.UserLoginDto;
import com.example.devwebtalk.dto.UserModifyDto;
import com.example.devwebtalk.entity.User;
import com.example.devwebtalk.repository.UserLoginRememberRepository;
import com.example.devwebtalk.repository.UserRepository;
import com.example.devwebtalk.setting.constant.Cons;
import com.example.devwebtalk.setting.util.CookieUtil;
import com.example.devwebtalk.setting.util.SEEDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.servlet.http.Cookie;
import java.util.Optional;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-07-10
 * Time: 오후 3:31
 */
@Service
public class UserService {
    public static final String SUCCESS = "success";
    public static final String NONUSER = "nonUser";
    public static final String WRONG_PASSWORD = "wrongPassword";

    @Autowired
    UserRepository userRepository;

    /**
     * 회원가입
     * @param user
     * @return
     */
    public Long join(User user) {
        User u = userRepository.save(user);
        return u.getId();
    }

    /**
     * 유저 찾기
     * @param user
     * @return
     */
    public Optional<User> findById(User user) {
        return findById(user.getId());
    }
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public String loginUser(String email, String pw) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()) return NONUSER;
        if(user.get().getPw().equals(pw)) return SUCCESS;
        else return WRONG_PASSWORD;
    }

    public void loginUser(UserLoginDto userLoginDto, BindingResult bindingResult) {
        final boolean existUser = isExistUser(userLoginDto);
        if (!existUser) {
            bindingResult.reject("notMatch.emailAndPw");
        }
    }

    private boolean isExistUser(UserLoginDto userLoginDto) {
        final String dbPw = userRepository.findByEmail(userLoginDto.getEmail())
                .orElseGet(User::new)
                .getPw();
        final boolean existUser = userLoginDto.getPw() != null &&
                userLoginDto.getPw().equals(dbPw);
        return existUser;
    }

    public UserModifyDto findUserModifyDtoByEmail(String email) {
        return userRepository.findUserModifyDtoByEmail(email);
    }
}
