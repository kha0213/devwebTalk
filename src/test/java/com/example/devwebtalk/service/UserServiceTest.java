package com.example.devwebtalk.service;

import com.example.devwebtalk.dto.UserCreateDto;
import com.example.devwebtalk.dto.UserModifyDto;
import com.example.devwebtalk.entity.User;
import com.example.devwebtalk.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

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

	@Test
	@DisplayName("UserCreateDto 유효성 검증 테스트")
	void UserCreateDtoValidationTest() {
		// given
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		final Validator validator = factory.getValidator();

		//when
		UserCreateDto emailPatternFailUser = new UserCreateDto("failEmail", "failEmail", "123456a!", "123456a!");
		UserCreateDto pwPatternFailUser = new UserCreateDto("test@gmail.com", "test@gmail.com", "failPw", "failPw");
		UserCreateDto emailAndCheckNotEqualUser = new UserCreateDto("test@gmail.com", "test2@gmail.com", "123456a!", "123456a!");
		UserCreateDto pwAndCheckNotEqualUser = new UserCreateDto("test@gmail.com", "test@gmail.com", "123456a!", "123456ab!");

		//then
		final Set<ConstraintViolation<UserCreateDto>> validate1 = validator.validate(emailPatternFailUser);
		assertThat(validatorHasParamMessage(validate1, "이메일 형식을 지켜주세요.")).isEqualTo(true);

		final Set<ConstraintViolation<UserCreateDto>> validate2 = validator.validate(pwPatternFailUser);
		assertThat(validatorHasParamMessage(validate2, "비밀 번호는 6~20자리로 숫자와 특수 문자가 포함된 영문 대소문자로 입력해 주세요")).isEqualTo(true);

		final Set<ConstraintViolation<UserCreateDto>> validate3 = validator.validate(emailAndCheckNotEqualUser);
		assertThat(validatorHasParamMessage(validate3, "이메일이 일치 하지 않습니다.")).isEqualTo(true);

		final Set<ConstraintViolation<UserCreateDto>> validate4 = validator.validate(pwAndCheckNotEqualUser);
		assertThat(validatorHasParamMessage(validate4, "비밀번호가 일치 하지 않습니다.")).isEqualTo(true);
	}

	/** errorMsg가 일치하는지 검증 메서드 */
	private <T> boolean validatorHasParamMessage(Set<ConstraintViolation<T>> validator, String errorMsg) {
		return validator.stream()
				.map(u -> u.getMessage())
				.anyMatch(u -> u.contains(errorMsg));
	}

	@Test
	@DisplayName("회원가입 테스트")
	void userJoinTest() {
		UserCreateDto paramUser = new UserCreateDto("test@gmail.com", "test@gmail.com", "123456a!", "123456a!");
		User user = paramUser.convertUser();

		//when
		Long userId = userService.join(user);
		User findUser = userService.findById(userId).orElseGet(User::new);

		//then
		assertThat(user).isEqualTo(findUser);
	}

	@Test
	@DisplayName("이메일로 UserModifyDto 반환 테스트")
	void findUserModifyDtoByEmailTest() {
		//given
		User dbUser = new User("user1","010-2017-4444","jeenpark@naver.com",null, "4444");

		//when
		UserModifyDto modifyDto = userService.findUserModifyDtoByEmail("jeenpark@naver.com");

		//then
		assertThat(modifyDto.getName()).isEqualTo(dbUser.getName());
		assertThat(modifyDto.getPhone()).isEqualTo(dbUser.getPhone());
	}
}