package com.example.devwebtalk.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FriendsGroup extends BaseEntity{
	@Id
	@GeneratedValue
	@Column(name = "FRIENDS_ID")
	private Long id;

	private String groupName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private User user;

	@OneToMany(mappedBy = "friendsGroup")
	private List<Friend> friends = new ArrayList<>();

	public FriendsGroup(String groupName, User user) {
		this.groupName = groupName;
		this.user = user;
		user.getFriendsGroups().add(this);
	}

	public void changeName(String groupName) {
		this.groupName = groupName;
	}
}
