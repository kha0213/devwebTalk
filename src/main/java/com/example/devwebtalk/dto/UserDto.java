package com.example.devwebtalk.dto;

import lombok.*;

import java.time.LocalDateTime;

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
public class UserDto {
	private String name;
	private String email;
	private LocalDateTime birthday;
}
