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
	private User friendUser;

	public Friend(FriendsGroup friendsGroup, User friendUser) {
		this.friendName = "";
		this.friendsGroup = friendsGroup;
		this.friendUser = friendUser;
		this.friendName = friendUser.getName();
		friendsGroup.getFriends().add(this);
	}

	public void changeName(String name) {
		this.friendName = name;
	}
}
