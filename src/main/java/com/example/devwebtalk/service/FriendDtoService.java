package com.example.devwebtalk.service;

import com.example.devwebtalk.dto.FriendDto;
import com.example.devwebtalk.entity.User;
import com.example.devwebtalk.repository.friend.FriendRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendDtoService {

	final
	FriendRepository friendRepository;

	public FriendDtoService(FriendRepository friendRepository) {
		this.friendRepository = friendRepository;
	}

	public List<FriendDto> getAllFriendList(User user) {
		return friendRepository.getListAll(user);
	}
}
