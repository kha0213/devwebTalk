package com.example.devwebtalk.service;

import com.example.devwebtalk.entity.FriendsGroup;
import com.example.devwebtalk.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class FriendServiceTest {
	@Autowired
	FriendService friendService;
	@PersistenceContext
	EntityManager em;

	@Test
	void createGroupTest() {
		User user = new User("A");
		em.persist(user);
		friendService.createGroup(user,"testGroup1");
		friendService.createGroup(user,"testGroup2");
		friendService.createGroup(user,"testGroup3");
		friendService.createGroup(user,"testGroup4");
		List<FriendsGroup> allGroups = friendService.getAllGroups(user);

		assertThat(allGroups.size()).isEqualTo(4);
		FriendsGroup friendsGroup = allGroups.get(allGroups.size() - 1);
		assertThat(friendsGroup.getGroupName()).isEqualTo("testGroup4");
		User resultUser = friendsGroup.getUser();
		assertThat(resultUser).isEqualTo(user);
	}

}