package com.example.devwebtalk.service;

import com.example.devwebtalk.entity.Friend;
import com.example.devwebtalk.entity.FriendsGroup;
import com.example.devwebtalk.entity.User;
import com.example.devwebtalk.repository.FriendRepository;
import com.example.devwebtalk.repository.FriendsGroupRepository;
import com.example.devwebtalk.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class InitDummyService {
	final UserRepository userRepository;
	final FriendRepository friendRepository;
	final FriendsGroupRepository friendsGroupRepository;

	public InitDummyService(UserRepository userRepository, FriendRepository friendRepository, FriendsGroupRepository friendsGroupRepository) {
		this.userRepository = userRepository;
		this.friendRepository = friendRepository;
		this.friendsGroupRepository = friendsGroupRepository;
	}

	@PersistenceContext
	EntityManager em;

	@PostConstruct
	@Transactional
	public void init() {
		User userA = new User("userA");
		User userB = new User("userB");
		User userC = new User("userC");
		userRepository.save(userA);
		userRepository.save(userB);
		userRepository.save(userC);

		FriendsGroup friendsGroupA = new FriendsGroup("", userA);
		friendsGroupRepository.save(friendsGroupA);
		Friend friendB = new Friend(friendsGroupA, userB);
		Friend friendC = new Friend(friendsGroupA, userC);

		friendRepository.save(friendB);
		friendRepository.save(friendC);
	}
}
