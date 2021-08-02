package com.example.devwebtalk.dto;

import lombok.Data;

@Data
public class FriendDto {
	private Long groupId;
	private Long friendId;
	private String friendName;
	private String groupName;
}
