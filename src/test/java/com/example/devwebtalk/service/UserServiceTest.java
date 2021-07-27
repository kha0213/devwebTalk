package com.example.devwebtalk.service;

import com.example.devwebtalk.entity.User;
import com.example.devwebtalk.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;

	@BeforeEach
	void dummyUser() {
		User user = new User("user1","010-2017-4444","jeenpark@naver.com",null, "4444");
		userRepository.save(user);
	}

	//로그인 테스트
	@Test
	void loginUser() {
		String wrongEmail = userService.loginUser("never@never.com", null);
		assertThat(wrongEmail).isEqualTo(UserService.NONUSER);

		String wrongPassword = userService.loginUser("jeenpark@naver.com", "3333");
		assertThat(wrongPassword).isEqualTo(UserService.WRONG_PASSWORD);

		String success = userService.loginUser("jeenpark@naver.com", "4444");
		assertThat(success).isEqualTo(UserService.SUCCESS);
	}
}