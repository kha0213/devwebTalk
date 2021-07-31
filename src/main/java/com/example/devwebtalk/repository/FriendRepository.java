package com.example.devwebtalk.repository;

import com.example.devwebtalk.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend, Long> {
}
