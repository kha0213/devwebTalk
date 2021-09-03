package com.example.devwebtalk.setting.aspect;

import com.example.devwebtalk.dto.UserLoginDto;
import com.example.devwebtalk.entity.User;
import com.example.devwebtalk.exception.NonExistentUserException;
import com.example.devwebtalk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@Aspect
@RequiredArgsConstructor
public class UserLoginAspect {

    private final UserRepository userRepository;

    @Around("execution(* com.example.devwebtalk.service.UserLoginRememberService.save(..)) && args(userLoginDto,..)")
    public Object userLoginDtoToUser(ProceedingJoinPoint joinPoint, UserLoginDto userLoginDto) throws Throwable {
        final String email = userLoginDto.getEmail();
        final Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            log.error("UserLoginRememberService.save ERROR user Not Found [email] {}", email);
            throw new NonExistentUserException("존재하지 않는 사용자입니다.");
        }
        return joinPoint.proceed();
    }
}
