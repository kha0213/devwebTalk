package com.example.devwebtalk.repository.friend;

import com.example.devwebtalk.dto.FriendDto;
import com.example.devwebtalk.entity.User;

import java.util.List;

public interface FriendRepositoryCustom {
	List<FriendDto> getListAll(User user);
}
