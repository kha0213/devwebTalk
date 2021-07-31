package com.example.devwebtalk.repository;

import com.example.devwebtalk.entity.FriendsGroup;
import com.example.devwebtalk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface FriendsGroupRepository extends JpaRepository<FriendsGroup, Long> {
	List<FriendsGroup> findByUser(User user);
}
