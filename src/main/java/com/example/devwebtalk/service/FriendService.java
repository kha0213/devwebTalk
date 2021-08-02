package com.example.devwebtalk.service;

import com.example.devwebtalk.entity.FriendsGroup;
import com.example.devwebtalk.entity.User;
import com.example.devwebtalk.repository.FriendRepository;
import com.example.devwebtalk.repository.FriendsGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {
	final FriendsGroupRepository friendsGroupRepository;
	final FriendRepository friendRepository;

	public FriendService(FriendsGroupRepository friendsGroupRepository, FriendRepository friendRepository) {
		this.friendsGroupRepository = friendsGroupRepository;
		this.friendRepository = friendRepository;
	}

	public Long createGroup(User user, String name){
		friendsGroupRepository.save(new FriendsGroup(name, user));
		return new FriendsGroup(name, user).getId();
	}

	public List<FriendsGroup> getAllGroups(User user) {
		return friendsGroupRepository.findByUser(user);
	}
}
