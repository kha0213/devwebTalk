package com.example.devwebtalk.service;

import com.example.devwebtalk.entity.Friend;
import com.example.devwebtalk.entity.FriendsGroup;
import com.example.devwebtalk.entity.User;
import com.example.devwebtalk.repository.FriendsGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class InitDummyService {
	final UserService userService;
	final FriendService friendService;
	final FriendsGroupRepository friendsGroupRepository;

	public InitDummyService(UserService userService, FriendService friendService, FriendsGroupRepository friendsGroupRepository) {
		this.userService = userService;
		this.friendService = friendService;
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
		userService.save(userA);
		userService.save(userB);
		userService.save(userC);

		FriendsGroup friendsGroupA = new FriendsGroup("", userA);
		friendsGroupRepository.save(friendsGroupA);
		Friend friendB = new Friend(friendsGroupA, userB);
		Friend friendC = new Friend(friendsGroupA, userC);

		em.persist(friendB);
		em.persist(friendC);
		em.flush();

		List<FriendsGroup> allGroups = friendService.getAllGroups(userA);
		System.out.println("allGroups = " + allGroups.toString());
	}
}
