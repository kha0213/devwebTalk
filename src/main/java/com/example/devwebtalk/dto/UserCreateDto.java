package com.example.devwebtalk.dto;

import com.example.devwebtalk.entity.User;
import lombok.*;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * View단으로 전달할 UserDto
 * 2021-07-26
 * Created By Kim Young Long
 * Blog : https://kha0213.github.io/
 * GitHub : https://github.com/kha0213
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ScriptAssert(lang = "javascript",
		script = "_this.email == _this.emailCheck",
		message = "이메일이 일치 하지 않습니다.")
@ScriptAssert(lang = "javascript",
		script = "_this.pw == _this.pwCheck",
		message = "비밀번호가 일치 하지 않습니다.")
public class UserCreateDto {

	@NotBlank
	@Email(message = "이메일 형식을 지켜주세요.")
	private String email;

	@NotBlank
	@Email
	private String emailCheck;

	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{6,20}$", message = "비밀 번호는 6~20자리로 숫자와 특수 문자가 포함된 영문 대소문자로 입력해 주세요")
	private String pw;

	@NotBlank
	private String pwCheck;

	public User convertUser() {
		return new User(email, pw);
	}
}
