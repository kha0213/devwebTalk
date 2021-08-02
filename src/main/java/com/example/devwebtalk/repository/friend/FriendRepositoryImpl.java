package com.example.devwebtalk.repository.friend;

import com.example.devwebtalk.dto.FriendDto;
import com.example.devwebtalk.entity.*;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

import static com.example.devwebtalk.entity.QFriend.*;
import static com.example.devwebtalk.entity.QFriendsGroup.*;
import static com.example.devwebtalk.entity.QUser.*;


public class FriendRepositoryImpl implements FriendRepositoryCustom{
	private final JPAQueryFactory queryFactory;

	public FriendRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public List<FriendDto> getListAll(User me) {
		return queryFactory
				.select(Projections.fields(FriendDto.class,
						friendsGroup.id.as("groupId"),
						user.id.as("friendId"),
						friend.friendName,
						friendsGroup.groupName))
				.from(friendsGroup)
				.join(friendsGroup.friends, friend)
				.join(friend.friendUser(), user)
				.where(friendsGroup.user().eq(me))
				.fetch();
	}
}
