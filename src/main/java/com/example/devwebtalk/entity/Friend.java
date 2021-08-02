package com.example.devwebtalk.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Friend extends BaseEntity{
	@Id
	@GeneratedValue
	@Column(name = "FRIEND_ID")
	private Long id;

	private String friendName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FRIENDS_ID")
	private FriendsGroup friendsGroup;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private User friend;

	public Friend(FriendsGroup friendsGroup, User friend) {
		this.friendName = "";
		this.friendsGroup = friendsGroup;
		this.friend = friend;
		this.friendName = friend.getName();
	}

	public void changeName(String name) {
		this.friendName = name;
	}
}
