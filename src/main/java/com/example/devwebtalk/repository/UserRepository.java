package com.example.devwebtalk.repository;

import com.example.devwebtalk.repository.custom.CustomUserRepository;
import com.example.devwebtalk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-07-10
 * Time: 오후 3:30
 */
public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {
	Optional<User> findByEmail(String email);


}
